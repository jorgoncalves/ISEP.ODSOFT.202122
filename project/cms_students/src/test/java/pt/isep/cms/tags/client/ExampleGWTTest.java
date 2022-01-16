package pt.isep.cms.tags.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.tags.client.presenter.TagsPresenter;
import pt.isep.cms.tags.client.view.TagsView;
import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
	private TagsPresenter tagsPresenter;
	private TagsServiceAsync rpcService;
	private HandlerManager eventBus;
	private TagsPresenter.Display mockDisplay;

	public String getModuleName() {
		return "pt.isep.cms.tags.TestCMSJUnit";
	}

	public void gwtSetUp() {
		rpcService = GWT.create(TagsService.class);
		mockDisplay = new TagsView();
		tagsPresenter = new TagsPresenter(rpcService, eventBus, mockDisplay);
	}

	public void testTagSort() {
		ArrayList<TagDetails> tagDetails = new ArrayList<TagDetails>();
		tagDetails.add(new TagDetails("0", "c_tag"));
		tagDetails.add(new TagDetails("1", "b_tag"));
		tagDetails.add(new TagDetails("2", "a_tag"));
		tagsPresenter.setTagDetails(tagDetails);
		tagsPresenter.sortTagDetails();
		assertTrue(tagsPresenter.getTagDetail(0).getDisplayName().equals("a_tag"));
		assertTrue(tagsPresenter.getTagDetail(1).getDisplayName().equals("b_tag"));
		assertTrue(tagsPresenter.getTagDetail(2).getDisplayName().equals("c_tag"));
	}

	public void testTagsService() {
		// Create the service that we will test.
		TagsServiceAsync tagsService = GWT.create(TagsService.class);
		ServiceDefTarget target = (ServiceDefTarget) tagsService;
		target.setServiceEntryPoint(GWT.getModuleBaseURL() + "tags/tagsService");

		// Since RPC calls are asynchronous, we will need to wait for a response
		// after this test method returns. This line tells the test runner to wait
		// up to 10 seconds before timing out.
		delayTestFinish(10000);

		// fail("Ainda nao implementado");

		// Send a request to the server.
		tagsService.getTag("2", new AsyncCallback<Tag>() {
			public void onFailure(Throwable caught) {
				// The request resulted in an unexpected error.
				fail("Request failure: " + caught.getMessage());
			}

			public void onSuccess(Tag result) {
				// Verify that the response is correct.
				assertNotNull(result);

				// Now that we have received a response, we need to tell the test runner
				// that the test is complete. You must call finishTest() after an
				// asynchronous test finishes successfully, or the test will time out.
				finishTest();
			}
		});
	}
}

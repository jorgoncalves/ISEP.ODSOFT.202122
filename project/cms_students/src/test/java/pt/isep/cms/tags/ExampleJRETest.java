package pt.isep.cms.tags;

import com.google.gwt.event.shared.HandlerManager;
import junit.framework.TestCase;
import pt.isep.cms.tags.client.TagsServiceAsync;
import pt.isep.cms.tags.client.presenter.TagsPresenter;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private TagsPresenter tagsPresenter;
	private TagsServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private TagsPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(TagsServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(TagsPresenter.Display.class);
		tagsPresenter = new TagsPresenter(mockRpcService, eventBus, mockDisplay);
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
}

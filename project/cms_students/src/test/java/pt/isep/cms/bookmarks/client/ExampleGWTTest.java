package pt.isep.cms.bookmarks.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.bookmarks.client.presenter.BookmarksPresenter;
import pt.isep.cms.bookmarks.client.view.BookmarksView;
import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
    private BookmarksPresenter bookmarksPresenter;
    private BookmarksServiceAsync rpcService;
    private HandlerManager eventBus;
    private BookmarksPresenter.Display mockDisplay;

    public String getModuleName() {
        return "pt.isep.cms.bookmarks.TestCMSJUnit";
    }

    public void gwtSetUp() {
        rpcService = GWT.create(BookmarksService.class);
        mockDisplay = new BookmarksView();
        bookmarksPresenter = new BookmarksPresenter(rpcService, eventBus, mockDisplay);
    }

    public void testBookmarkSort() {
        ArrayList<BookmarkDetails> bookmarkDetails = new ArrayList<BookmarkDetails>();
        bookmarkDetails.add(new BookmarkDetails("0", "c_bookmark"));
        bookmarkDetails.add(new BookmarkDetails("1", "b_bookmark"));
        bookmarkDetails.add(new BookmarkDetails("2", "a_bookmark"));
        bookmarksPresenter.setBookmarkDetails(bookmarkDetails);
        bookmarksPresenter.sortBookmarkDetails();
        assertTrue(bookmarksPresenter.getBookmarkDetail(0).getDisplayName().equals("a_bookmark"));
        assertTrue(bookmarksPresenter.getBookmarkDetail(1).getDisplayName().equals("b_bookmark"));
        assertTrue(bookmarksPresenter.getBookmarkDetail(2).getDisplayName().equals("c_bookmark"));
    }

    public void testBookmarksService() {
        // Create the service that we will test.
        BookmarksServiceAsync bookmarksService = GWT.create(BookmarksService.class);
        ServiceDefTarget target = (ServiceDefTarget) bookmarksService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "bookmarks/bookmarksService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(10000);

        // fail("Ainda nao implementado");

        // Send a request to the server.
        bookmarksService.getBookmark("2", new AsyncCallback<Bookmark>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(Bookmark result) {
                // Verify that the response is correct.
                assertTrue(result != null);

                // Now that we have received a response, we need to tell the test runner
                // that the test is complete. You must call finishTest() after an
                // asynchronous test finishes successfully, or the test will time out.
                finishTest();
            }
        });
    }
}

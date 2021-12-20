package pt.isep.cms.bookmarks;

import com.google.gwt.event.shared.HandlerManager;
import junit.framework.TestCase;
import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.bookmarks.client.presenter.BookmarksPresenter;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

import java.util.ArrayList;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private BookmarksPresenter bookmarksPresenter;
	private BookmarksServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private BookmarksPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(BookmarksServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(BookmarksPresenter.Display.class);
		bookmarksPresenter = new BookmarksPresenter(mockRpcService, eventBus, mockDisplay);
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
}

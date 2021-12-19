package pt.isep.cms.bookmarks.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

import java.util.ArrayList;

public interface BookmarksServiceAsync {

  public void addBookmark(Bookmark bookmark, AsyncCallback<Bookmark> callback);
  public void deleteBookmark(String id, AsyncCallback<Boolean> callback);
  public void deleteBookmarks(ArrayList<String> ids, AsyncCallback<ArrayList<BookmarkDetails>> callback);
  public void getBookmarkDetails(AsyncCallback<ArrayList<BookmarkDetails>> callback);
  public void getBookmark(String id, AsyncCallback<Bookmark> callback);
  public void updateBookmark(Bookmark bookmark, AsyncCallback<Bookmark> callback);
}


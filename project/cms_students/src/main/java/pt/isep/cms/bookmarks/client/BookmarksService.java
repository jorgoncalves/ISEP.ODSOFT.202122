package pt.isep.cms.bookmarks.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("bookmarksService")
public interface BookmarksService extends RemoteService {
	
  Bookmark addBookmark(Bookmark bookmark);
  Boolean deleteBookmark(String id); 
  ArrayList<BookmarkDetails> deleteBookmarks(ArrayList<String> ids);
  ArrayList<BookmarkDetails> getBookmarkDetails();
  Bookmark getBookmark(String id);
  Bookmark updateBookmark(Bookmark bookmark);
}

package pt.isep.cms.bookmarks.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.bookmarks.client.BookmarksService;
import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

@SuppressWarnings("serial")
public class BookmarksServiceImpl extends RemoteServiceServlet implements
        BookmarksService {

    private static final String[] bookmarksNoteData = new String[]{
            "note1", "note2", "note3", "note4",
    };

    private final Date[] bookmarksCreationData = new Date[]{
            new Date(), new Date(), new Date(), new Date(),
    };

    private final HashMap<String, Bookmark> bookmarks = new HashMap<String, Bookmark>();

    public BookmarksServiceImpl() {
        initBookmarks();
    }

    private void initBookmarks() {
        // TODO: Create a real UID for each bookmark
        //
        for (int i = 0; i < bookmarksNoteData.length && i < bookmarksCreationData.length; ++i) {
            Bookmark bookmark = new Bookmark(String.valueOf(i), bookmarksNoteData[i], bookmarksCreationData[i]);
            bookmarks.put(bookmark.getId(), bookmark);
        }
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        bookmark.setId(String.valueOf(bookmarks.size()));
        bookmarks.put(bookmark.getId(), bookmark);
        return bookmark;
    }

    public Bookmark updateBookmark(Bookmark bookmark) {
        String lid = bookmark.getId();
        bookmarks.remove(bookmark.getId());
        bookmarks.put(bookmark.getId(), bookmark);
        return bookmark;
    }

    public Boolean deleteBookmark(String id) {
        bookmarks.remove(id);
        return true;
    }

    public ArrayList<BookmarkDetails> deleteBookmarks(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteBookmark(ids.get(i));
        }

        return getBookmarkDetails();
    }

    public ArrayList<BookmarkDetails> getBookmarkDetails() {
        ArrayList<BookmarkDetails> bookmarkDetails = new ArrayList<BookmarkDetails>();

        Iterator<String> it = bookmarks.keySet().iterator();
        while (it.hasNext()) {
            Bookmark bookmark = bookmarks.get(it.next());
            bookmarkDetails.add(bookmark.getLightWeightBookmark());
        }

        return bookmarkDetails;
    }

    public Bookmark getBookmark(String id) {
        return bookmarks.get(id);
    }
}

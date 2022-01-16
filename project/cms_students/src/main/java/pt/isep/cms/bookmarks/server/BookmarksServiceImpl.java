package pt.isep.cms.bookmarks.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.*;

import pt.isep.cms.bookmarks.client.BookmarksService;
import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings("serial")
public class BookmarksServiceImpl extends RemoteServiceServlet implements
        BookmarksService {

    private static final String[] bookmarksNoteData = new String[]{
            "note1", "note2", "note3", "note4",
    };

    private final Date[] bookmarksCreationData = new Date[]{
            new Date(), new Date(), new Date(), new Date(),
    };

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public BookmarksServiceImpl() {

        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentBookmarks();
    }

    private void initPersistentBookmarks() {

        Query query = entitymanager.createQuery("Select COUNT(b) from Bookmark b");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            System.out.println("No bookmarks found. Populating db.");
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < bookmarksNoteData.length; ++i) {

                Bookmark bookmark = new Bookmark(bookmarksNoteData[i], bookmarksCreationData[i]);
                bookmark.setId(String.valueOf(i));
                this.entitymanager.persist(bookmark);
            }

            this.entitymanager.getTransaction().commit();

        }
    }

    public Bookmark addBookmark(Bookmark bookmark) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(bookmark);
        this.entitymanager.getTransaction().commit();

        return bookmark;
    }

    public Bookmark updateBookmark(Bookmark bookmark) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(bookmark);
        this.entitymanager.getTransaction().commit();

        return bookmark;
    }

    public Boolean deleteBookmark(String id) {
        this.entitymanager.getTransaction().begin();
        Bookmark bookmark = entitymanager.find(Bookmark.class, id);
        entitymanager.remove(bookmark);
        this.entitymanager.getTransaction().commit();

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

        Query query = entitymanager.createQuery("Select c from Bookmark c");

        @SuppressWarnings("unchecked")
        List<Bookmark> list = query.getResultList();

        for (Bookmark bookmark : list) {
            bookmarkDetails.add(bookmark.getLightWeightBookmark());
        }
        return bookmarkDetails;
    }

    public Bookmark getBookmark(String id) {
        Bookmark bookmark = entitymanager.find(Bookmark.class, id);
        return bookmark;
    }
}

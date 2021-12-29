package pt.isep.cms.books.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pt.isep.cms.books.client.BooksService;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.shared.BookDetails;
import pt.isep.cms.contacts.shared.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings("serial")
public class BooksServiceImpl extends RemoteServiceServlet implements
        BooksService {

    private static final String[] booksTitleData = new String[]{
            "Mutant Academy", "Iceman: The Melt Down", "Burger Boy",
            "World of Yesterday", "The Cover Up", "Secrets Of Mara",
            "The Blue Knight", "Cupcake Kitty", "One Boy And The World",
            "Dead Man’s WIsh", "A Boring Day", "The Enchanted Ones",
            "One Boy And The World", "Cattles And Horses", "Aliens of a New Kind",
            "The Hollow Spirit", "Time To Go", "Dead Man’s WIsh",
            "One Boy And The World", "The Number", "Who Is Mary Walker?",
            "Arealia, Forest Princess"};
    private final String[] booksAuthorData = new String[]{
            "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
            "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
            "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard",
            "Louis", "Andrews", "English", "Snedden"};

    private final String[] booksISBNData = new String[]{
            "0-3020-4050-1", "0-3297-9686-0", "0-7162-8596-7",
            "0-9038-4488-5", "0-4757-9808-2", "0-9370-1872-4",
            "0-3749-7243-5", "0-3629-0985-7m", "0-5620-1952-9",
            "0-3948-1778-8", "0-9264-8338-2", "0-3789-4704-4",
            "0-1366-8313-4", "0-3684-4316-7", "0-5349-2715-7",
            "0-7086-2171-6", "0-6789-6941-8", "0-2010-6355-7",
            "0-9927-4435-0", "0-6634-6102-2", "0-9456-5188-0",
            "0-9791-0893-4"};

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public BooksServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentBooks();
    }

    private void initPersistentBooks() {
        Query query = entitymanager.createQuery("Select COUNT(b) from Book b");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            System.out.println("No books found. Populating db.");
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < booksTitleData.length && i < booksAuthorData.length && i < booksISBNData.length; ++i) {
                Book book = new Book(booksTitleData[i], booksAuthorData[i], booksISBNData[i]);
                this.entitymanager.persist(book);
            }

            this.entitymanager.getTransaction().commit();

        }
    }

    public Book addBook(Book book) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(book);
        this.entitymanager.getTransaction().commit();

        return book;
    }

    public Book updateBook(Book book) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(book);
        this.entitymanager.getTransaction().commit();

        return book;
    }

    public Boolean deleteBook(String id) {
        this.entitymanager.getTransaction().begin();
        Book book = entitymanager.find(Book.class, id);
        entitymanager.remove(book);
        this.entitymanager.getTransaction().commit();

        return true;
    }

    public ArrayList<BookDetails> deleteBooks(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteBook(ids.get(i));
        }

        return getBookDetails();
    }

    public ArrayList<BookDetails> getBookDetails() {
        ArrayList<BookDetails> bookDetails = new ArrayList<BookDetails>();

        Query query = entitymanager.createQuery("Select b from Book b");

        @SuppressWarnings("unchecked")
        List<Book> list = query.getResultList();

        for (Book book : list) {
            bookDetails.add(book.getLightWeightBook());
        }

        return bookDetails;
    }

    public Book getBook(String id) {
        Book book = entitymanager.find(Book.class, id);

        return book;
    }
}

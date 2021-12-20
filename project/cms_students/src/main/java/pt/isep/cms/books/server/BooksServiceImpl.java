package pt.isep.cms.books.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.books.client.BooksService;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.shared.BookDetails;

@SuppressWarnings("serial")
public class BooksServiceImpl extends RemoteServiceServlet implements
    BooksService {

  private static final String[] booksFirstNameData = new String[] {
      "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
      "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
      "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", 
      "Abigail", "Chad", "Terry", "Bell"};
  
  private final String[] booksLastNameData = new String[] {
      "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
      "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
      "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard", 
      "Louis", "Andrews", "English", "Snedden"};
  
  private final String[] booksEmailData = new String[] {
      "mark@example.com", "hollie@example.com", "boticario@example.com",
      "emerson@example.com", "healy@example.com", "brigitte@example.com",
      "elba@example.com", "claudio@example.com", "dena@example.com",
      "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
      "qetlyxxogg@example.com", "antenas_sul@example.com",
      "cblake@example.com", "gailh@example.com", "orville@example.com",
      "post_master@example.com", "rchilders@example.com", "buster@example.com",
      "user31065@example.com", "ftsgeolbx@example.com"};
      
  private final HashMap<String, Book> books = new HashMap<String, Book>();

  public BooksServiceImpl() {
    initBooks();
  }
  
  private void initBooks() {
    // TODO: Create a real UID for each book
    //
    for (int i = 0; i < booksFirstNameData.length && i < booksLastNameData.length && i < booksEmailData.length; ++i) {
      Book book = new Book(String.valueOf(i), booksFirstNameData[i], booksLastNameData[i], booksEmailData[i]);
      books.put(book.getId(), book);
    }
  }
  
  public Book addBook(Book book) {
    book.setId(String.valueOf(books.size()));
    books.put(book.getId(), book);
    return book;
  }

  public Book updateBook(Book book) {
	  String lid=book.getId();
    books.remove(book.getId());
    books.put(book.getId(), book);
    return book;
  }

  public Boolean deleteBook(String id) {
    books.remove(id);
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
    
    Iterator<String> it = books.keySet().iterator();
    while(it.hasNext()) { 
      Book book = books.get(it.next());
      bookDetails.add(book.getLightWeightBook());
    }
    
    return bookDetails;
  }

  public Book getBook(String id) {
    return books.get(id);
  }
}

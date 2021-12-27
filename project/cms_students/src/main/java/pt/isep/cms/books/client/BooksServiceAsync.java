package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.shared.BookDetails;

import java.util.ArrayList;

public interface BooksServiceAsync {

  public void addBook(Book book, AsyncCallback<Book> callback);
  public void deleteBook(String id, AsyncCallback<Boolean> callback);
  public void deleteBooks(ArrayList<String> ids, AsyncCallback<ArrayList<BookDetails>> callback);
  public void getBookDetails(AsyncCallback<ArrayList<BookDetails>> callback);
  public void getBook(String id, AsyncCallback<Book> callback);
  public void updateBook(Book book, AsyncCallback<Book> callback);
}


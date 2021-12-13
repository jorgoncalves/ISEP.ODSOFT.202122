package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.cms.books.shared.Book;

import java.util.ArrayList;

public interface BooksServiceAsync {

    public void addBook(Book book, AsyncCallback<Book> callback);
    public void deleteBooks(ArrayList<String> ids, AsyncCallback<ArrayList<Book>>  callback);
    public void getBook(String id, AsyncCallback<Book> callback);
    void getBooks(AsyncCallback<ArrayList<Book>> callback);
    public void updateBook(Book book, AsyncCallback<Book> callback);
}

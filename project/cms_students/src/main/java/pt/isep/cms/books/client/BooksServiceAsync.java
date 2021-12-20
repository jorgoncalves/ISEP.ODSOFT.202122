package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.contacts.shared.ContactDetails;

import java.util.ArrayList;

public interface BooksServiceAsync {

    public void addBook(Book book, AsyncCallback<Book> callback);
    public void deleteBooks(ArrayList<String> ids, AsyncCallback<ArrayList<Book>> callback);
    public void getBook(String id, AsyncCallback<Book> callback);
    public void getBooks(ArrayList<Book> books ,AsyncCallback<ArrayList<Book>> callback);
    public void updateBook(Book book, AsyncCallback<Book> callback);
}

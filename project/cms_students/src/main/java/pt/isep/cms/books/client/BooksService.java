package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.RemoteService;
import pt.isep.cms.books.shared.Book;

import java.util.ArrayList;

public interface BooksService  extends RemoteService {

    Book addBook(Book book);
    ArrayList<Book> deleteBooks(ArrayList<String> ids);
    Book getBook(String id);
    ArrayList<Book> getBooks(ArrayList<Book> book);
    Book updateBook(Book Book);
}
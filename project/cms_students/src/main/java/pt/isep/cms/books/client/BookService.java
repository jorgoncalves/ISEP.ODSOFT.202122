package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.RemoteService;
import pt.isep.cms.books.shared.Book;

import java.util.ArrayList;

public interface BookService  extends RemoteService {

    Book addBook(Book book);
    Boolean deleteBooks(ArrayList<String> ids);
    Book getBook(String id);
    ArrayList<String> getBooks(ArrayList<String> ids);
    Book updateBook(Book Book);
}
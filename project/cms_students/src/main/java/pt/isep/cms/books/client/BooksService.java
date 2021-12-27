package pt.isep.cms.books.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.shared.BookDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("booksService")
public interface BooksService extends RemoteService {
	
  Book addBook(Book book);
  Boolean deleteBook(String id);
  ArrayList<BookDetails> deleteBooks(ArrayList<String> ids);
  ArrayList<BookDetails> getBookDetails();
  Book getBook(String id);
  Book updateBook(Book book);
}

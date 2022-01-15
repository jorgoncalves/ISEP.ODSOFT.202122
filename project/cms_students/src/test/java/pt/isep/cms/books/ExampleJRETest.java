package pt.isep.cms.books;

import com.google.gwt.event.shared.HandlerManager;
import junit.framework.TestCase;
import pt.isep.cms.books.client.presenter.BooksPresenter;
import pt.isep.cms.books.shared.BookDetails;
import pt.isep.cms.books.client.BooksServiceAsync;

import java.util.ArrayList;

import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.assertTrue;

public class ExampleJRETest extends TestCase {
    private BooksPresenter booksPresenter;
    private BooksServiceAsync mockRpcService;
    private HandlerManager eventBus;
    private BooksPresenter.Display mockDisplay;

    protected void setUp() {
        mockRpcService = createStrictMock(BooksServiceAsync.class);
        eventBus = new HandlerManager(null);
        mockDisplay = createStrictMock(BooksPresenter.Display.class);
        booksPresenter = new BooksPresenter(mockRpcService, eventBus, mockDisplay);
    }

    public void testBookSort() {
        ArrayList<BookDetails> bookDetails = new ArrayList<BookDetails>();
        bookDetails.add(new BookDetails("0", "c_book"));
        bookDetails.add(new BookDetails("1", "b_book"));
        bookDetails.add(new BookDetails("2", "a_book"));
        booksPresenter.setBookDetails(bookDetails);
        booksPresenter.sortBookDetails();
        assertTrue(booksPresenter.getBookDetail(0).getDisplayName().equals("a_book"));
        assertTrue(booksPresenter.getBookDetail(1).getDisplayName().equals("b_book"));
        assertTrue(booksPresenter.getBookDetail(2).getDisplayName().equals("c_book"));
    }
}

package pt.isep.cms.books.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.books.client.presenter.BooksPresenter;
import pt.isep.cms.books.client.view.BooksView;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.shared.BookDetails;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
    private BooksPresenter booksPresenter;
    private BooksServiceAsync rpcService;
    private HandlerManager eventBus;
    private BooksPresenter.Display mockDisplay;

    @Override
    public String getModuleName() {
        return "pt.isep.cms.books.TestCMSJUnit";
    }

    public void gwtSetUp() {
        rpcService = GWT.create(BooksService.class);
        mockDisplay = new BooksView();
        booksPresenter = new BooksPresenter(rpcService, eventBus, mockDisplay);
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

    public void testBooksService() {
        // Create the service that we will test.
        BooksServiceAsync booksService = GWT.create(BooksService.class);
        ServiceDefTarget target = (ServiceDefTarget) booksService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "books/booksService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(10000);

        // fail("Ainda nao implementado");

        // Send a request to the server.
        booksService.getBook("2", new AsyncCallback<Book>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(Book result) {
                // Verify that the response is correct.
                assertTrue(result != null);

                // Now that we have received a response, we need to tell the test runner
                // that the test is complete. You must call finishTest() after an
                // asynchronous test finishes successfully, or the test will time out.
                finishTest();
            }
        });
    }
}

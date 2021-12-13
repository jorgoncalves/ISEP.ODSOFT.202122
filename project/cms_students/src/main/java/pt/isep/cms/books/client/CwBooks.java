package pt.isep.cms.books.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import pt.isep.cms.client.ContentWidget;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.books.client.BookService;
import pt.isep.cms.books.client.BooksServiceAsync;

/**
 * Main Content Widget for Books.
 */
public class CwBooks extends ContentWidget {

    /**
     * The constants used in this Content Widget.
     */
    // This is only for generation, so we disable it
    public static interface CwConstants extends Constants {

        String cwTitle();
        String cwISBN();
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    /**
     * Constructor.
     *
     * @param constants
     *            the constants
     */
    public CwBooks(ShowcaseConstants constants) {
        super(constants.cwTitle(), constants.cwISBN());
        this.globalConstants = constants;
        this.constants = constants;
    }

    /**
     * Initialize this example.
     */
    @Override
    public Widget onInitialize() {
        // The service should be created on GWT module loading
        BooksServiceAsync rpcService = GWT.create(BookService.class);

        // Should setup the Presenter Panel for the Contacts....
        VerticalPanel vPanel = new VerticalPanel();

        HandlerManager eventBus = new HandlerManager(null);
        BooksController appViewer = new BooksController(rpcService, eventBus, this.globalConstants);
        appViewer.go(vPanel);

        // Return the panel
        return vPanel;
    }
}

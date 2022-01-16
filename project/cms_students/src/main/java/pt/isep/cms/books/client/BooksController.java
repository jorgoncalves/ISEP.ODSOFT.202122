package pt.isep.cms.books.client;

import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.books.client.event.AddBookEvent;
import pt.isep.cms.books.client.event.AddBookEventHandler;
import pt.isep.cms.books.client.event.BookUpdatedEvent;
import pt.isep.cms.books.client.event.BookUpdatedEventHandler;
import pt.isep.cms.books.client.event.EditBookEvent;
import pt.isep.cms.books.client.event.EditBookEventHandler;
import pt.isep.cms.books.client.event.EditBookCancelledEvent;
import pt.isep.cms.books.client.event.EditBookCancelledEventHandler;
import pt.isep.cms.books.client.presenter.BooksPresenter;
import pt.isep.cms.books.client.presenter.EditBookPresenter;
import pt.isep.cms.books.client.presenter.Presenter;
import pt.isep.cms.books.client.view.BooksView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.books.client.view.BooksDialog;
import pt.isep.cms.tags.client.TagsServiceAsync;

public class BooksController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final BooksServiceAsync rpcService;
    private final TagsServiceAsync tagRpcService;
    private final BookmarksServiceAsync bookmarkRpcService;
    private HasWidgets container;

    public static interface CwConstants extends Constants {
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    public BooksController(BooksServiceAsync rpcService, TagsServiceAsync tagsRpcService, BookmarksServiceAsync bookmarkRpcService, HandlerManager eventBus, ShowcaseConstants constants) {
        this.tagRpcService = tagsRpcService;
        this.bookmarkRpcService = bookmarkRpcService;
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.constants = constants;
        this.globalConstants = constants;

        bind();
    }

    private void bind() {
        // (ATB) No History at this level
        // History.addValueChangeHandler(this);

        eventBus.addHandler(AddBookEvent.TYPE, new AddBookEventHandler() {
            public void onAddBook(AddBookEvent event) {
                doAddNewBook();
            }
        });

        eventBus.addHandler(EditBookEvent.TYPE, new EditBookEventHandler() {
            public void onEditBook(EditBookEvent event) {
                doEditBook(event.getId());
            }
        });

        eventBus.addHandler(EditBookCancelledEvent.TYPE, new EditBookCancelledEventHandler() {
            public void onEditBookCancelled(EditBookCancelledEvent event) {
                doEditBookCancelled();
            }
        });

        eventBus.addHandler(BookUpdatedEvent.TYPE, new BookUpdatedEventHandler() {
            public void onBookUpdated(BookUpdatedEvent event) {
                doBookUpdated();
            }
        });
    }

    private void doAddNewBook() {
        // Lets use the presenter to display a dialog...
        Presenter presenter = new EditBookPresenter(rpcService, tagRpcService, bookmarkRpcService, eventBus, new BooksDialog(globalConstants, BooksDialog.Type.ADD, tagRpcService, bookmarkRpcService));
        presenter.go(container);

    }

    private void doEditBook(String id) {
        Presenter presenter = new EditBookPresenter(rpcService, tagRpcService, bookmarkRpcService, eventBus, new BooksDialog(globalConstants, BooksDialog.Type.UPDATE, tagRpcService, bookmarkRpcService), id);
        presenter.go(container);
    }

    private void doEditBookCancelled() {
        // Nothing to update...
    }

    private void doBookUpdated() {
        // (ATB) Update the list of books...
        Presenter presenter = new BooksPresenter(rpcService, eventBus, new BooksView());
        presenter.go(container);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        Presenter presenter = new BooksPresenter(rpcService, eventBus, new BooksView());
        presenter.go(container);
    }

}

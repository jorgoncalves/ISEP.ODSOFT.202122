package pt.isep.cms.books.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.books.client.event.*;
import pt.isep.cms.books.client.presenter.BooksPresenter;
import pt.isep.cms.books.client.presenter.EditBookPresenter;
import pt.isep.cms.books.client.view.BooksDialog;
import pt.isep.cms.books.client.view.BooksView;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.contacts.client.presenter.Presenter;

public class BooksController  implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final BooksServiceAsync rpcService;
    private HasWidgets container;

    public static interface CwConstants extends Constants {
    }

    /**
     * An instance of the constants.
     */
    private final CwConstants constants;
    private final ShowcaseConstants globalConstants;

    public BooksController(BooksServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
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

    private void doEditBookCancelled() {
        // Nothing to update...
    }

    private void doAddNewBook() {
        // Lets use the presenter to display a dialog...
        Presenter presenter = new EditBookPresenter(rpcService, eventBus, new BooksDialog(globalConstants, BooksDialog.Type.ADD));
        presenter.go(container);

    }

    private void doEditBook(String id) {
        Presenter presenter = new EditBookPresenter(rpcService, eventBus, new BooksDialog(globalConstants, BooksDialog.Type.UPDATE), id);
        presenter.go(container);
    }


    private void doBookUpdated() {
        // (ATB) Update the list of contacts...
        Presenter presenter = new BooksPresenter(rpcService, eventBus, new BooksView());
        presenter.go(container);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        Presenter presenter = new BooksPresenter(rpcService, eventBus, new BooksView());
        presenter.go(container);
    }

}

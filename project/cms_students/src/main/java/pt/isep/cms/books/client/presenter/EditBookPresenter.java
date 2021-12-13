package pt.isep.cms.books.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;

import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.books.client.BooksServiceAsync;
import pt.isep.cms.books.client.event.BookUpdatedEvent;
import pt.isep.cms.books.client.event.EditBookCancelledEvent;
import pt.isep.cms.contacts.client.event.ContactUpdatedEvent;
import pt.isep.cms.contacts.client.event.EditContactCancelledEvent;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.contacts.client.presenter.Presenter;

public class EditBookPresenter  implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getTitle();

        HasValue<String> getISBN();

        void show();

        void hide();
    }

    private Book book;
    private final BooksServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditBookPresenter(BooksServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.book = new Book();
        this.display = display;
        bind();
    }

    public EditBookPresenter(BooksServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getBook(id, new AsyncCallback<Book>() {
            public void onSuccess(Book result) {
                book = result;
                EditBookPresenter.this.display.getTitle().setValue(book.getTitle());
                EditBookPresenter.this.display.getISBN().setValue(book.getISBN());

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving Book");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
                display.hide();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                display.hide();
                eventBus.fireEvent(new EditBookCancelledEvent());
            }
        });
    }

    public void go(final HasWidgets container) {
        display.show();
    }

    private void doSave() {
        book.setTile(display.getTitle().getValue());
        book.setISBN(display.getISBN().getValue());

        if (book.getId() == null) {
            // Adding new Book
            rpcService.addBook(book, new AsyncCallback<Book>() {
                public void onSuccess(Book result) {
                    eventBus.fireEvent(new BookUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error adding Book");
                }
            });
        } else {
            // updating existing Book
            rpcService.updateBook(book, new AsyncCallback<Book>() {
                public void onSuccess(Book result) {
                    eventBus.fireEvent(new BookUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error updating Book");
                }
            });
        }
    }

}


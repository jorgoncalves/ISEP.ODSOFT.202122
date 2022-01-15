package pt.isep.cms.books.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.books.client.BooksServiceAsync;
import pt.isep.cms.books.client.event.BookUpdatedEvent;
import pt.isep.cms.books.client.event.EditBookCancelledEvent;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.tags.shared.Tag;

import java.util.List;

public class EditBookPresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getTile();

        HasValue<String> getAuthor();

        HasValue<String> getISBN();

        List<Tag> getTags();

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
                EditBookPresenter.this.display.getTile().setValue(book.getTitle());
                EditBookPresenter.this.display.getAuthor().setValue(book.getAuthor());
                EditBookPresenter.this.display.getISBN().setValue(book.getISBN());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving book");
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
        book.setTile(display.getTile().getValue());
        book.setAuthor(display.getAuthor().getValue());
        book.setISBN(display.getISBN().getValue());
        book.setTags(display.getTags());

        if (book.getId() == null) {
            // Adding new book
            rpcService.addBook(book, new AsyncCallback<Book>() {
                public void onSuccess(Book result) {
                    eventBus.fireEvent(new BookUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error adding book");
                }
            });
        } else {
            // updating existing book
            rpcService.updateBook(book, new AsyncCallback<Book>() {
                public void onSuccess(Book result) {
                    eventBus.fireEvent(new BookUpdatedEvent(result));
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error updating book");
                }
            });
        }
    }

}

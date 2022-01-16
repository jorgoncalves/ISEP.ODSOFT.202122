package pt.isep.cms.books.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.books.client.BooksServiceAsync;
import pt.isep.cms.books.client.event.BookUpdatedEvent;
import pt.isep.cms.books.client.event.EditBookCancelledEvent;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.tags.client.TagsServiceAsync;
import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;
import java.util.List;

public class EditBookPresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<String> getTile();

        HasValue<String> getAuthor();

        HasValue<String> getISBN();

        ListBox getTags();

        void show();

        void hide();
    }

    private Book book;
    private final BooksServiceAsync rpcService;
    private final TagsServiceAsync tagsRpcService;
    private final BookmarksServiceAsync bookmarksRpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditBookPresenter(BooksServiceAsync rpcService, TagsServiceAsync tagsRpcService, BookmarksServiceAsync bookmarksRpcService, HandlerManager eventBus, Display display) {
        this.tagsRpcService = tagsRpcService;
        this.bookmarksRpcService = bookmarksRpcService;
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.book = new Book();
        this.display = display;
        bind();
    }

    public EditBookPresenter(BooksServiceAsync rpcService, TagsServiceAsync tagsRpcService, BookmarksServiceAsync bookmarksRpcService, HandlerManager eventBus, Display display, String id) {
        this.tagsRpcService = tagsRpcService;
        this.bookmarksRpcService = bookmarksRpcService;
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
                EditBookPresenter.this.display.getTags().setMultipleSelect(true);
                for (Tag tag : book.getTags()) {
                    for (int i = 0; i < EditBookPresenter.this.display.getTags().getItemCount() - 1; i++) {
                        String id = EditBookPresenter.this.display.getTags().getValue(i);
                        if (id.equals(tag.getId())) {
                            EditBookPresenter.this.display.getTags().setSelectedIndex(i);
                        }
                    }
                }

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

        List<Tag> tagsArr = new ArrayList<Tag>();
        for (int i = 0; i < display.getTags().getItemCount() - 1; i++) {
            if (display.getTags().isItemSelected(i)) {
                Tag newTag = new Tag();
                newTag.setId(display.getTags().getValue(i));
                newTag.setDescription(display.getTags().getItemText(i));
                tagsArr.add(newTag);
            }
        }

        book.setTags(tagsArr);

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

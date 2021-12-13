package pt.isep.cms.books.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import pt.isep.cms.books.client.BooksServiceAsync;

import pt.isep.cms.books.client.event.EditBookEvent;
import pt.isep.cms.contacts.client.event.AddContactEvent;
import pt.isep.cms.contacts.client.event.EditContactEvent;

import pt.isep.cms.books.client.presenter.BooksPresenter;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.contacts.client.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;

public class BooksPresenter implements Presenter {

    private List<Book> books;

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getDeleteButton();

        HasClickHandlers getList();

        void setData(List<String> data);

        int getClickedRow(ClickEvent event);

        List<Integer> getSelectedRows();

        Widget asWidget();
    }

    private final BooksServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final BooksPresenter.Display display;

    public BooksPresenter(BooksServiceAsync rpcService, HandlerManager eventBus, BooksPresenter.Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {
        display.getAddButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new AddContactEvent());
            }
        });

        display.getDeleteButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteSelectedBooks();
            }
        });

        display.getList().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int selectedRow = display.getClickedRow(event);

                if (selectedRow >= 0) {
                    String id = books.get(selectedRow).getId();
                    eventBus.fireEvent(new EditBookEvent(id));
                }
            }
        });
    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
        fetchBooks();
    }

    public void sortBooks() {

        // Yes, we could use a more optimized method of sorting, but the
        // point is to create a test case that helps illustrate the higher
        // level concepts used when creating MVP-based applications.
        //
        for (int i = 0; i < books.size(); ++i) {
            for (int j = 0; j < books.size() - 1; ++j) {
                if (books.get(j).title
                        .compareToIgnoreCase(books.get(j + 1).getTitle()) >= 0) {
                    Book tmp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, tmp);
                }
            }
        }
    }

    public void setBook(List<Book> books) {
        this.books = books;
    }

    public Book getBook(int index) {
        return books.get(index);
    }

    private void fetchBooks() {
        rpcService.getBooks(new AsyncCallback<ArrayList<Book>>() {
            public void onSuccess(ArrayList<Book> result) {
                books = result;
                sortBooks();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(books.get(i).getTitle());
                }

                display.setData(data);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching Books");
            }
        });
    }

    private void deleteSelectedBooks() {
        List<Integer> selectedRows = display.getSelectedRows();
        ArrayList<String> ids = new ArrayList<String>();

        for (int i = 0; i < selectedRows.size(); ++i) {
            ids.add(books.get(selectedRows.get(i)).getId());
        }

        rpcService.deleteBooks(ids, new AsyncCallback<ArrayList<Book>>() {
            public void onSuccess(ArrayList<Book> result) {
                books = result;
                sortBooks();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(books.get(i).getTitle());
                }

                display.setData(data);

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error deleting selected book");
            }
        });
    }
}

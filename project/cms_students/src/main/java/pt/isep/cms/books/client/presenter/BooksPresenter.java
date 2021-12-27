package pt.isep.cms.books.client.presenter;

import pt.isep.cms.books.client.BooksServiceAsync;
import pt.isep.cms.books.client.event.AddBookEvent;
import pt.isep.cms.books.client.event.EditBookEvent;
import pt.isep.cms.books.shared.BookDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

public class BooksPresenter implements Presenter {

	private List<BookDetails> bookDetails;

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
	private final Display display;

	public BooksPresenter(BooksServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddBookEvent());
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
					String id = bookDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditBookEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchBookDetails();
	}

	public void sortBookDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < bookDetails.size(); ++i) {
			for (int j = 0; j < bookDetails.size() - 1; ++j) {
				if (bookDetails.get(j).getDisplayName()
						.compareToIgnoreCase(bookDetails.get(j + 1).getDisplayName()) >= 0) {
					BookDetails tmp = bookDetails.get(j);
					bookDetails.set(j, bookDetails.get(j + 1));
					bookDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setBookDetails(List<BookDetails> bookDetails) {
		this.bookDetails = bookDetails;
	}

	public BookDetails getBookDetail(int index) {
		return bookDetails.get(index);
	}

	private void fetchBookDetails() {
		rpcService.getBookDetails(new AsyncCallback<ArrayList<BookDetails>>() {
			public void onSuccess(ArrayList<BookDetails> result) {
				bookDetails = result;
				sortBookDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(bookDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching book details");
			}
		});
	}

	private void deleteSelectedBooks() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(bookDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteBooks(ids, new AsyncCallback<ArrayList<BookDetails>>() {
			public void onSuccess(ArrayList<BookDetails> result) {
				bookDetails = result;
				sortBookDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(bookDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected books");
			}
		});
	}
}

package pt.isep.cms.bookmarks.client.presenter;

import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.bookmarks.client.event.AddBookmarkEvent;
import pt.isep.cms.bookmarks.client.event.EditBookmarkEvent;
import pt.isep.cms.bookmarks.shared.BookmarkDetails;

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

public class BookmarksPresenter implements Presenter {

	private List<BookmarkDetails> bookmarkDetails;

	public interface Display {
		HasClickHandlers getAddButton();

		HasClickHandlers getDeleteButton();

		HasClickHandlers getList();

		void setData(List<String> data);

		int getClickedRow(ClickEvent event);

		List<Integer> getSelectedRows();

		Widget asWidget();
	}

	private final BookmarksServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public BookmarksPresenter(BookmarksServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddBookmarkEvent());
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				deleteSelectedBookmarks();
			}
		});

		display.getList().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int selectedRow = display.getClickedRow(event);

				if (selectedRow >= 0) {
					String id = bookmarkDetails.get(selectedRow).getId();
					eventBus.fireEvent(new EditBookmarkEvent(id));
				}
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());

		fetchBookmarkDetails();
	}

	public void sortBookmarkDetails() {

		// Yes, we could use a more optimized method of sorting, but the
		// point is to create a test case that helps illustrate the higher
		// level concepts used when creating MVP-based applications.
		//
		for (int i = 0; i < bookmarkDetails.size(); ++i) {
			for (int j = 0; j < bookmarkDetails.size() - 1; ++j) {
				if (bookmarkDetails.get(j).getDisplayName()
						.compareToIgnoreCase(bookmarkDetails.get(j + 1).getDisplayName()) >= 0) {
					BookmarkDetails tmp = bookmarkDetails.get(j);
					bookmarkDetails.set(j, bookmarkDetails.get(j + 1));
					bookmarkDetails.set(j + 1, tmp);
				}
			}
		}
	}

	public void setBookmarkDetails(List<BookmarkDetails> bookmarkDetails) {
		this.bookmarkDetails = bookmarkDetails;
	}

	public BookmarkDetails getBookmarkDetail(int index) {
		return bookmarkDetails.get(index);
	}

	private void fetchBookmarkDetails() {
		rpcService.getBookmarkDetails(new AsyncCallback<ArrayList<BookmarkDetails>>() {
			public void onSuccess(ArrayList<BookmarkDetails> result) {
				bookmarkDetails = result;
				sortBookmarkDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(bookmarkDetails.get(i).getDisplayName());
				}

				display.setData(data);
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error fetching bookmark details");
			}
		});
	}

	private void deleteSelectedBookmarks() {
		List<Integer> selectedRows = display.getSelectedRows();
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < selectedRows.size(); ++i) {
			ids.add(bookmarkDetails.get(selectedRows.get(i)).getId());
		}

		rpcService.deleteBookmarks(ids, new AsyncCallback<ArrayList<BookmarkDetails>>() {
			public void onSuccess(ArrayList<BookmarkDetails> result) {
				bookmarkDetails = result;
				sortBookmarkDetails();
				List<String> data = new ArrayList<String>();

				for (int i = 0; i < result.size(); ++i) {
					data.add(bookmarkDetails.get(i).getDisplayName());
				}

				display.setData(data);

			}

			public void onFailure(Throwable caught) {
				Window.alert("Error deleting selected bookmarks");
			}
		});
	}
}

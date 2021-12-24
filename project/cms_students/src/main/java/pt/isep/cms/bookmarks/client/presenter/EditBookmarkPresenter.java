package pt.isep.cms.bookmarks.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.Window;
import pt.isep.cms.bookmarks.client.BookmarksServiceAsync;
import pt.isep.cms.bookmarks.client.event.BookmarkUpdatedEvent;
import pt.isep.cms.bookmarks.client.event.EditBookmarkCancelledEvent;
import pt.isep.cms.bookmarks.shared.Bookmark;

import java.util.Date;

public class EditBookmarkPresenter implements Presenter {
	public interface Display {
		HasClickHandlers getSaveButton();

		HasClickHandlers getCancelButton();

		HasValue<String> getNote();

		HasValue<Date> getCreationDate();

		void show();

		void hide();
	}

	private Bookmark bookmark;
	private final BookmarksServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public EditBookmarkPresenter(BookmarksServiceAsync rpcService, HandlerManager eventBus, Display display) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.bookmark = new Bookmark();
		this.display = display;
		bind();
	}

	public EditBookmarkPresenter(BookmarksServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = display;
		bind();

		rpcService.getBookmark(id, new AsyncCallback<Bookmark>() {
			public void onSuccess(Bookmark result) {
				bookmark = result;
				EditBookmarkPresenter.this.display.getNote().setValue(bookmark.getNote());
				EditBookmarkPresenter.this.display.getCreationDate().setValue(bookmark.getCreationDate());
			}

			public void onFailure(Throwable caught) {
				Window.alert("Error retrieving bookmark");
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
				eventBus.fireEvent(new EditBookmarkCancelledEvent());
			}
		});
	}

	public void go(final HasWidgets container) {
		display.show();
	}

	private void doSave() {
		bookmark.setNote(display.getNote().getValue());
		bookmark.setCreationDate(display.getCreationDate().getValue());

		if (bookmark.getId() == null) {
			// Adding new bookmark
			rpcService.addBookmark(bookmark, new AsyncCallback<Bookmark>() {
				public void onSuccess(Bookmark result) {
					eventBus.fireEvent(new BookmarkUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error adding bookmark");
				}
			});
		} else {
			// updating existing bookmark
			rpcService.updateBookmark(bookmark, new AsyncCallback<Bookmark>() {
				public void onSuccess(Bookmark result) {
					eventBus.fireEvent(new BookmarkUpdatedEvent(result));
				}

				public void onFailure(Throwable caught) {
					Window.alert("Error updating bookmark");
				}
			});
		}
	}

}

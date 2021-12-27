package pt.isep.cms.bookmarks.client;

import pt.isep.cms.client.ShowcaseConstants;

import pt.isep.cms.bookmarks.client.event.AddBookmarkEvent;
import pt.isep.cms.bookmarks.client.event.AddBookmarkEventHandler;
import pt.isep.cms.bookmarks.client.event.BookmarkUpdatedEvent;
import pt.isep.cms.bookmarks.client.event.BookmarkUpdatedEventHandler;
import pt.isep.cms.bookmarks.client.event.EditBookmarkEvent;
import pt.isep.cms.bookmarks.client.event.EditBookmarkEventHandler;
import pt.isep.cms.bookmarks.client.event.EditBookmarkCancelledEvent;
import pt.isep.cms.bookmarks.client.event.EditBookmarkCancelledEventHandler;
import pt.isep.cms.bookmarks.client.presenter.BookmarksPresenter;
import pt.isep.cms.bookmarks.client.presenter.EditBookmarkPresenter;
import pt.isep.cms.bookmarks.client.presenter.Presenter;
import pt.isep.cms.bookmarks.client.view.BookmarksView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;

import pt.isep.cms.bookmarks.client.view.BookmarksDialog;

public class BookmarksController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final BookmarksServiceAsync rpcService;
	private HasWidgets container;

	public static interface CwConstants extends Constants {
	}

	/**
	 * An instance of the constants.
	 */
	private final CwConstants constants;
	private final ShowcaseConstants globalConstants;

	public BookmarksController(BookmarksServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.constants = constants;
		this.globalConstants = constants;

		bind();
	}

	private void bind() {
		// (ATB) No History at this level
		// History.addValueChangeHandler(this);

		eventBus.addHandler(AddBookmarkEvent.TYPE, new AddBookmarkEventHandler() {
			public void onAddBookmark(AddBookmarkEvent event) {
				doAddNewBookmark();
			}
		});

		eventBus.addHandler(EditBookmarkEvent.TYPE, new EditBookmarkEventHandler() {
			public void onEditBookmark(EditBookmarkEvent event) {
				doEditBookmark(event.getId());
			}
		});

		eventBus.addHandler(EditBookmarkCancelledEvent.TYPE, new EditBookmarkCancelledEventHandler() {
			public void onEditBookmarkCancelled(EditBookmarkCancelledEvent event) {
				doEditBookmarkCancelled();
			}
		});

		eventBus.addHandler(BookmarkUpdatedEvent.TYPE, new BookmarkUpdatedEventHandler() {
			public void onBookmarkUpdated(BookmarkUpdatedEvent event) {
				doBookmarkUpdated();
			}
		});
	}

	private void doAddNewBookmark() {
		// Lets use the presenter to display a dialog...
		Presenter presenter = new EditBookmarkPresenter(rpcService, eventBus, new BookmarksDialog(globalConstants, BookmarksDialog.Type.ADD));
		presenter.go(container);

	}

	private void doEditBookmark(String id) {
		Presenter presenter = new EditBookmarkPresenter(rpcService, eventBus, new BookmarksDialog(globalConstants, BookmarksDialog.Type.UPDATE), id);
		presenter.go(container);
	}

	private void doEditBookmarkCancelled() {
		// Nothing to update...
	}

	private void doBookmarkUpdated() {
		// (ATB) Update the list of bookmarks...
		Presenter presenter = new BookmarksPresenter(rpcService, eventBus, new BookmarksView());
		presenter.go(container);
	}

	public void go(final HasWidgets container) {
		this.container = container;

		Presenter presenter = new BookmarksPresenter(rpcService, eventBus, new BookmarksView());
		presenter.go(container);
	}

}

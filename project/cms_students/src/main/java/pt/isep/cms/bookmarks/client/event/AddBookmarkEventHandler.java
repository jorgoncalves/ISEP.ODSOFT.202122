package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddBookmarkEventHandler extends EventHandler {
  void onAddBookmark(AddBookmarkEvent event);
}

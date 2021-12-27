package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditBookmarkEventHandler extends EventHandler {
  void onEditBookmark(EditBookmarkEvent event);
}

package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditBookmarkCancelledEventHandler extends EventHandler {
  void onEditBookmarkCancelled(EditBookmarkCancelledEvent event);
}

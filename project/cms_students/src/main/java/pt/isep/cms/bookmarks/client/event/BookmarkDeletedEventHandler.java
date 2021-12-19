package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BookmarkDeletedEventHandler extends EventHandler {
  void onBookmarkDeleted(BookmarkDeletedEvent event);
}

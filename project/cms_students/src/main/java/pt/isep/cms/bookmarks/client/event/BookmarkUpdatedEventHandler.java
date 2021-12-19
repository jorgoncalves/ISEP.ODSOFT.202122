package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BookmarkUpdatedEventHandler extends EventHandler{
  void onBookmarkUpdated(BookmarkUpdatedEvent event);
}

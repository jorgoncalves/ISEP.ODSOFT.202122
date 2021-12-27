package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BookDeletedEventHandler extends EventHandler {
  void onBookDeleted(BookDeletedEvent event);
}

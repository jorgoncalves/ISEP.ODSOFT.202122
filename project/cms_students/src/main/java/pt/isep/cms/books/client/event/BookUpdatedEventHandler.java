package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface BookUpdatedEventHandler extends EventHandler{
  void onBookUpdated(BookUpdatedEvent event);
}

package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddBookEventHandler extends EventHandler {
  void onAddBook(AddBookEvent event);
}

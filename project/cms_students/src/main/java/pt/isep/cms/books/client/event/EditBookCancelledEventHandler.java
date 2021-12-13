package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface EditBookCancelledEventHandler extends EventHandler {
  void onEditBookCancelled(EditBookCancelledEvent event);
}

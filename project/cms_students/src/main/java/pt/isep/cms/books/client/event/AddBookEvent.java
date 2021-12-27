package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddBookEvent extends GwtEvent<AddBookEventHandler> {
  public static Type<AddBookEventHandler> TYPE = new Type<AddBookEventHandler>();
  
  @Override
  public Type<AddBookEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddBookEventHandler handler) {
    handler.onAddBook(this);
  }
}

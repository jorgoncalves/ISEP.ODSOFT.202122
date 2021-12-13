package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBookCancelledEvent extends GwtEvent<EditBookCancelledEventHandler>{
  public static Type<EditBookCancelledEventHandler> TYPE = new Type<EditBookCancelledEventHandler>();
  
  @Override
  public Type<EditBookCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBookCancelledEventHandler handler) {
    handler.onEditBookCancelled(this);
  }
}

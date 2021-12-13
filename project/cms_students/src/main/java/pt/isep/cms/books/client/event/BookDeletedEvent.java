package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BookDeletedEvent extends GwtEvent<BookDeletedEventHandler>{
  public static Type<BookDeletedEventHandler> TYPE = new Type<BookDeletedEventHandler>();
  
  @Override
  public Type<BookDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BookDeletedEventHandler handler) {
    handler.onBookDeleted(this);
  }
}

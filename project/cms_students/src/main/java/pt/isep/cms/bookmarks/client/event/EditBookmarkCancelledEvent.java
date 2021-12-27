package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBookmarkCancelledEvent extends GwtEvent<EditBookmarkCancelledEventHandler>{
  public static Type<EditBookmarkCancelledEventHandler> TYPE = new Type<EditBookmarkCancelledEventHandler>();
  
  @Override
  public Type<EditBookmarkCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBookmarkCancelledEventHandler handler) {
    handler.onEditBookmarkCancelled(this);
  }
}

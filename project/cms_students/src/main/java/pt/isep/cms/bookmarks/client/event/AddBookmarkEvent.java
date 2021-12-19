package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddBookmarkEvent extends GwtEvent<AddBookmarkEventHandler> {
  public static Type<AddBookmarkEventHandler> TYPE = new Type<AddBookmarkEventHandler>();
  
  @Override
  public Type<AddBookmarkEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddBookmarkEventHandler handler) {
    handler.onAddBookmark(this);
  }
}

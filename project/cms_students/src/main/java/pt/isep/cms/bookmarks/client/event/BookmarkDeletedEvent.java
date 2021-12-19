package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class BookmarkDeletedEvent extends GwtEvent<BookmarkDeletedEventHandler>{
  public static Type<BookmarkDeletedEventHandler> TYPE = new Type<BookmarkDeletedEventHandler>();
  
  @Override
  public Type<BookmarkDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BookmarkDeletedEventHandler handler) {
    handler.onBookmarkDeleted(this);
  }
}

package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.bookmarks.shared.Bookmark;

public class BookmarkUpdatedEvent extends GwtEvent<BookmarkUpdatedEventHandler>{
  public static Type<BookmarkUpdatedEventHandler> TYPE = new Type<BookmarkUpdatedEventHandler>();
  private final Bookmark updatedBookmark;
  
  public BookmarkUpdatedEvent(Bookmark updatedBookmark) {
    this.updatedBookmark = updatedBookmark;
  }
  
  public Bookmark getUpdatedBookmark() { return updatedBookmark; }
  

  @Override
  public Type<BookmarkUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(BookmarkUpdatedEventHandler handler) {
    handler.onBookmarkUpdated(this);
  }
}

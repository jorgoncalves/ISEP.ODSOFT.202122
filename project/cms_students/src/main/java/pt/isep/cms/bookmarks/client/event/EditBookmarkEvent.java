package pt.isep.cms.bookmarks.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBookmarkEvent extends GwtEvent<EditBookmarkEventHandler>{
  public static Type<EditBookmarkEventHandler> TYPE = new Type<EditBookmarkEventHandler>();
  private final String id;
  
  public EditBookmarkEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditBookmarkEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBookmarkEventHandler handler) {
    handler.onEditBookmark(this);
  }
}

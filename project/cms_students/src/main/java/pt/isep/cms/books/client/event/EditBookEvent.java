package pt.isep.cms.books.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditBookEvent extends GwtEvent<EditBookEventHandler>{
  public static Type<EditBookEventHandler> TYPE = new Type<EditBookEventHandler>();
  private final String id;
  
  public EditBookEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditBookEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditBookEventHandler handler) {
    handler.onEditBook(this);
  }
}

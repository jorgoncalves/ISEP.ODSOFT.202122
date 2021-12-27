package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTagEvent extends GwtEvent<EditTagEventHandler> {
  public static Type<EditTagEventHandler> TYPE = new Type<EditTagEventHandler>();
  private final String id;

  public EditTagEvent(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public Type<EditTagEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditTagEventHandler handler) {
    handler.onEditTag(this);
  }
}

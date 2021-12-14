package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddTagEvent extends GwtEvent<AddTagEventHandler> {
  public static Type<AddTagEventHandler> TYPE = new Type<AddTagEventHandler>();

  @Override
  public Type<AddTagEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddTagEventHandler handler) {
    handler.onAddTag(this);
  }
}

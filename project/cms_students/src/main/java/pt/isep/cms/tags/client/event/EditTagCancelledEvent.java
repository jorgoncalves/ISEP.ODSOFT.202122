package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditTagCancelledEvent extends GwtEvent<EditTagCancelledEventHandler> {
  public static Type<EditTagCancelledEventHandler> TYPE = new Type<EditTagCancelledEventHandler>();

  @Override
  public Type<EditTagCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditTagCancelledEventHandler handler) {
    handler.onEditTagCancelled(this);
  }
}

package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class TagDeletedEvent extends GwtEvent<TagDeletedEventHandler> {
  public static Type<TagDeletedEventHandler> TYPE = new Type<TagDeletedEventHandler>();

  @Override
  public Type<TagDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TagDeletedEventHandler handler) {
    handler.onTagDeleted(this);
  }
}

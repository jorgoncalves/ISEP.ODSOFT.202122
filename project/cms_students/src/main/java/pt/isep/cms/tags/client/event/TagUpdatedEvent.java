package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.tags.shared.Tag;

public class TagUpdatedEvent extends GwtEvent<TagUpdatedEventHandler> {
  public static Type<TagUpdatedEventHandler> TYPE = new Type<TagUpdatedEventHandler>();
  private final Tag updatedTag;

  public TagUpdatedEvent(Tag updatedTag) {
    this.updatedTag = updatedTag;
  }

  public Tag getUpdatedTag() {
    return updatedTag;
  }

  @Override
  public Type<TagUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TagUpdatedEventHandler handler) {
    handler.onTagUpdated(this);
  }
}

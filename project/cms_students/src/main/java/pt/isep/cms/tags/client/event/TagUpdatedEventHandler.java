package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TagUpdatedEventHandler extends EventHandler {
  void onTagUpdated(TagUpdatedEvent event);
}

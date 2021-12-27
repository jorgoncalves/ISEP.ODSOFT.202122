package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface TagDeletedEventHandler extends EventHandler {
  void onTagDeleted(TagDeletedEvent event);
}

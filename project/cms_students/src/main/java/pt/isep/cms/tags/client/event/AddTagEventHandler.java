package pt.isep.cms.tags.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddTagEventHandler extends EventHandler {
  void onAddTag(AddTagEvent event);
}

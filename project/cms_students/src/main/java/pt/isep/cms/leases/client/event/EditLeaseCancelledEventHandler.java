package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.EventHandler;
import pt.isep.cms.leases.client.event.EditLeaseCancelledEvent;

public interface EditLeaseCancelledEventHandler extends EventHandler {
  void onEditLeaseCancelled(EditLeaseCancelledEvent event);
}

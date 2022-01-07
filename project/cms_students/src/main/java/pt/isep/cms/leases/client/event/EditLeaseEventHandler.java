package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.EventHandler;
import pt.isep.cms.leases.client.event.EditLeaseEvent;

public interface EditLeaseEventHandler extends EventHandler {
  void onEditLease(EditLeaseEvent event);
}


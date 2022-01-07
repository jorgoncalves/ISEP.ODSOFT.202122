package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.EventHandler;
import pt.isep.cms.leases.client.event.AddLeaseEvent;

public interface AddLeaseEventHandler extends EventHandler {
  void onAddLease(AddLeaseEvent event);
}

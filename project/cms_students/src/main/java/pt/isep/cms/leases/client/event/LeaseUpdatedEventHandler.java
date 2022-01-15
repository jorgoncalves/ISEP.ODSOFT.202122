package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.EventHandler;
import pt.isep.cms.leases.client.event.LeaseUpdatedEvent;

public interface LeaseUpdatedEventHandler extends EventHandler{
  void onLeaseUpdated(LeaseUpdatedEvent event);
}

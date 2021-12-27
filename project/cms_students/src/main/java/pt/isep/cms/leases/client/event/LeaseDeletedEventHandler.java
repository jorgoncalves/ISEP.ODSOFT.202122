package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.EventHandler;
import pt.isep.cms.leases.client.event.LeaseDeletedEvent;

public interface LeaseDeletedEventHandler extends EventHandler {
  void onLeaseDeleted(LeaseDeletedEvent event);
}

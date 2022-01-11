package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.leases.client.event.LeaseDeletedEventHandler;

public class LeaseDeletedEvent extends GwtEvent<LeaseDeletedEventHandler> {
    public static Type<LeaseDeletedEventHandler> TYPE = new Type<LeaseDeletedEventHandler>();

    @Override
    public Type<LeaseDeletedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LeaseDeletedEventHandler handler) {
        handler.onLeaseDeleted(this);
    }

}

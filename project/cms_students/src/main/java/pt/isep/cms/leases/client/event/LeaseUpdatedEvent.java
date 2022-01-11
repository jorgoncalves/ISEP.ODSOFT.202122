package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.leases.client.event.LeaseUpdatedEventHandler;
import pt.isep.cms.leases.shared.Lease;

public class LeaseUpdatedEvent extends GwtEvent<LeaseUpdatedEventHandler> {
    public static Type<LeaseUpdatedEventHandler> TYPE = new Type<LeaseUpdatedEventHandler>();
    private final Lease updatedLease;

    public LeaseUpdatedEvent(Lease updatedLease) {
        this.updatedLease = updatedLease;
    }

    public Lease getUpdatedLease() { return updatedLease; }


    @Override
    public Type<LeaseUpdatedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LeaseUpdatedEventHandler handler) {
        handler.onLeaseUpdated(this);
    }

}

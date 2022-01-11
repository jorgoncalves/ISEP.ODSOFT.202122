package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.leases.client.event.AddLeaseEventHandler;

public class AddLeaseEvent extends GwtEvent<AddLeaseEventHandler> {
    public static Type<AddLeaseEventHandler> TYPE = new Type<AddLeaseEventHandler>();

    @Override
    public Type<AddLeaseEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddLeaseEventHandler handler) {
        handler.onAddLease(this);
    }
}

package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.leases.client.event.EditLeaseEventHandler;

public class EditLeaseEvent extends GwtEvent<EditLeaseEventHandler> {
    public static Type<EditLeaseEventHandler> TYPE = new Type<EditLeaseEventHandler>();
    private final String id;

    public EditLeaseEvent(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    @Override
    public Type<EditLeaseEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditLeaseEventHandler handler) {
        handler.onEditLease(this);
    }
}

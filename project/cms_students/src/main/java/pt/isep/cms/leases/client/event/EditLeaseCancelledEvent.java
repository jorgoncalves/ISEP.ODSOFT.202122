package pt.isep.cms.leases.client.event;

import com.google.gwt.event.shared.GwtEvent;
import pt.isep.cms.leases.client.event.EditLeaseCancelledEventHandler;

public class EditLeaseCancelledEvent extends GwtEvent<EditLeaseCancelledEventHandler> {
    public static Type<EditLeaseCancelledEventHandler> TYPE = new Type<EditLeaseCancelledEventHandler>();

    @Override
    public Type<EditLeaseCancelledEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditLeaseCancelledEventHandler handler) {
        handler.onEditLeaseCancelled(this);
    }
}

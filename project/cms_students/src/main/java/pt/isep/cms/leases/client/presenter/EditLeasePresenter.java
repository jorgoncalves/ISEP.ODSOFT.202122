package pt.isep.cms.leases.client.presenter;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.leases.client.LeasesServiceAsync;
import pt.isep.cms.leases.client.event.LeaseUpdatedEvent;
import pt.isep.cms.leases.client.event.EditLeaseCancelledEvent;
import pt.isep.cms.leases.client.presenter.EditLeasePresenter;
import pt.isep.cms.leases.shared.Lease;

public class EditLeasePresenter implements Presenter {
    public interface Display {
        HasClickHandlers getSaveButton();

        HasClickHandlers getCancelButton();

        HasValue<Date> getOnDate();

        HasValue<Date> getToDate();

        HasValue<String> getBook();

        HasValue<String> getLeasesContact();

        void show();

        void hide();
    }

    private Lease lease;
    private final LeasesServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final EditLeasePresenter.Display display;

    public EditLeasePresenter(LeasesServiceAsync rpcService, HandlerManager eventBus, EditLeasePresenter.Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.lease = new Lease();
        this.display = display;
        bind();
    }

    public EditLeasePresenter(LeasesServiceAsync rpcService, HandlerManager eventBus, EditLeasePresenter.Display display, String id) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getLease(id, new AsyncCallback<Lease>() {
            public void onSuccess(Lease result) {
                lease = result;
                EditLeasePresenter.this.display.getOnDate().setValue(lease.getOnDate());
                EditLeasePresenter.this.display.getToDate().setValue(lease.getToDate());
                EditLeasePresenter.this.display.getBook().setValue(lease.getBook());
                EditLeasePresenter.this.display.getLeasesContact().setValue(lease.getleaseContact());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving lease");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
                display.hide();
            }
        });

        this.display.getCancelButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                display.hide();
                eventBus.fireEvent(new EditLeaseCancelledEvent());
            }
        });
    }

    public void go(final HasWidgets container) {
        display.show();
    }

    private void doSave() {
        lease.setOnDate(display.getOnDate().getValue());
        lease.setToDate(display.getToDate().getValue());
        lease.setBook(display.getBook().getValue());
        lease.setleaseContact(display.getLeasesContact().getValue());

        rpcService.validLease(lease, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error validating lease");
            }

            @Override
            public void onSuccess(Boolean result) {
                if (!result) {
                    Window.alert("A book cannot be leased to different contacts at the same time");
                } else {
                    if (lease.getId() == null) {
                        // Adding new lease
                        rpcService.addLease(lease, new AsyncCallback<Lease>() {
                            public void onSuccess(Lease result) {
                                eventBus.fireEvent(new LeaseUpdatedEvent(result));
                            }

                            public void onFailure(Throwable caught) {
                                Window.alert("Error adding lease");
                            }
                        });
                    } else {
                        // updating existing lease
                        rpcService.updateLease(lease, new AsyncCallback<Lease>() {
                            public void onSuccess(Lease result) {
                                eventBus.fireEvent(new LeaseUpdatedEvent(result));
                            }

                            public void onFailure(Throwable caught) {
                                Window.alert("Error updating lease");
                            }
                        });
                    }
                }
            }
        });
    }
}

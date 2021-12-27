package pt.isep.cms.leases.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.isep.cms.client.ShowcaseConstants;
import pt.isep.cms.leases.client.LeasesController;
import pt.isep.cms.leases.client.LeasesServiceAsync;
import pt.isep.cms.leases.client.event.*;
import pt.isep.cms.leases.client.presenter.LeasesPresenter;
import pt.isep.cms.leases.client.presenter.EditLeasePresenter;
import pt.isep.cms.leases.client.presenter.Presenter;
import pt.isep.cms.leases.client.view.LeasesDialog;
import pt.isep.cms.leases.client.view.LeasesView;

public class LeasesController implements Presenter { // (ATB) No history at this level, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final LeasesServiceAsync rpcService;
    private HasWidgets container;

    public static interface CwConstants extends Constants {
    }

    /**
     * An instance of the constants.
     */
    private final LeasesController.CwConstants constants;
    private final ShowcaseConstants globalConstants;

    public LeasesController(LeasesServiceAsync rpcService, HandlerManager eventBus, ShowcaseConstants constants) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        this.constants = constants;
        this.globalConstants = constants;

        bind();
    }

    private void bind() {
        // (ATB) No History at this level
        // History.addValueChangeHandler(this);

        eventBus.addHandler(AddLeaseEvent.TYPE, new AddLeaseEventHandler() {
            public void onAddLease(AddLeaseEvent event) {
                doAddNewLease();
            }
        });

        eventBus.addHandler(EditLeaseEvent.TYPE, new EditLeaseEventHandler() {
            public void onEditLease(EditLeaseEvent event) {
                doEditLease(event.getId());
            }
        });

        eventBus.addHandler(EditLeaseCancelledEvent.TYPE, new EditLeaseCancelledEventHandler() {
            public void onEditLeaseCancelled(EditLeaseCancelledEvent event) {
                doEditLeaseCancelled();
            }
        });

        eventBus.addHandler(LeaseUpdatedEvent.TYPE, new LeaseUpdatedEventHandler() {
            public void onLeaseUpdated(LeaseUpdatedEvent event) {
                doLeaseUpdated();
            }
        });
    }

    private void doAddNewLease() {
        // Lets use the presenter to display a dialog...
        Presenter presenter = new EditLeasePresenter(rpcService, eventBus, new LeasesDialog(globalConstants, LeasesDialog.Type.ADD));
        presenter.go(container);

    }

    private void doEditLease(String id) {
        Presenter presenter = new EditLeasePresenter(rpcService, eventBus, new LeasesDialog(globalConstants, LeasesDialog.Type.UPDATE), id);
        presenter.go(container);
    }

    private void doEditLeaseCancelled() {
        // Nothing to update...
    }

    private void doLeaseUpdated() {
        // (ATB) Update the list of leases...
        Presenter presenter = new LeasesPresenter(rpcService, eventBus, new LeasesView());
        presenter.go(container);
    }

    public void go(final HasWidgets container) {
        this.container = container;

        Presenter presenter = new LeasesPresenter(rpcService, eventBus, new LeasesView());
        presenter.go(container);
    }
}

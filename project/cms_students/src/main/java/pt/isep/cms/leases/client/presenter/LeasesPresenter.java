package pt.isep.cms.leases.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import pt.isep.cms.leases.client.LeasesServiceAsync;
import pt.isep.cms.leases.client.event.AddLeaseEvent;
import pt.isep.cms.leases.client.event.EditLeaseEvent;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;
import java.util.List;

public class LeasesPresenter implements Presenter {
    private List<LeaseDetails> leaseDetails;

    public interface Display {
        HasClickHandlers getAddButton();

        HasClickHandlers getDeleteButton();

        HasClickHandlers getList();

        void setData(List<String> data);

        int getClickedRow(ClickEvent event);

        List<Integer> getSelectedRows();

        Widget asWidget();
    }

    private final LeasesServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final LeasesPresenter.Display display;

    public LeasesPresenter(LeasesServiceAsync rpcService, HandlerManager eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
    }

    public void bind() {
        display.getAddButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                eventBus.fireEvent(new AddLeaseEvent());
            }
        });

        display.getDeleteButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                deleteSelectedLeases();
            }
        });

        display.getList().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                int selectedRow = display.getClickedRow(event);

                if (selectedRow >= 0) {
                    String id = leaseDetails.get(selectedRow).getId();
                    eventBus.fireEvent(new EditLeaseEvent(id));
                }
            }
        });
    }

    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());

        fetchLeaseDetails();
    }

    public void sortLeaseDetails() {

        // Yes, we could use a more optimized method of sorting, but the
        // point is to create a test case that helps illustrate the higher
        // level concepts used when creating MVP-based applications.
        //
        for (int i = 0; i < leaseDetails.size(); ++i) {
            for (int j = 0; j < leaseDetails.size() - 1; ++j) {
                if (leaseDetails.get(j).getDisplayName()
                        .compareToIgnoreCase(leaseDetails.get(j + 1).getDisplayName()) >= 0) {
                    LeaseDetails tmp = leaseDetails.get(j);
                    leaseDetails.set(j, leaseDetails.get(j + 1));
                    leaseDetails.set(j + 1, tmp);
                }
            }
        }
    }

    public void setLeaseDetails(List<LeaseDetails> leaseDetails) {
        this.leaseDetails = leaseDetails;
    }

    public LeaseDetails getLeaseDetail(int index) {
        return leaseDetails.get(index);
    }

    private void fetchLeaseDetails() {
        rpcService.getLeaseDetails(new AsyncCallback<ArrayList<LeaseDetails>>() {
            public void onSuccess(ArrayList<LeaseDetails> result) {
                leaseDetails = result;
                sortLeaseDetails();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(leaseDetails.get(i).getDisplayName());
                }

                display.setData(data);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching lease details");
            }
        });
    }

    private void deleteSelectedLeases() {
        List<Integer> selectedRows = display.getSelectedRows();
        ArrayList<String> ids = new ArrayList<String>();

        for (int i = 0; i < selectedRows.size(); ++i) {
            ids.add(leaseDetails.get(selectedRows.get(i)).getId());
        }

        rpcService.deleteLeases(ids, new AsyncCallback<ArrayList<LeaseDetails>>() {
            public void onSuccess(ArrayList<LeaseDetails> result) {
                leaseDetails = result;
                sortLeaseDetails();
                List<String> data = new ArrayList<String>();

                for (int i = 0; i < result.size(); ++i) {
                    data.add(leaseDetails.get(i).getDisplayName());
                }

                display.setData(data);

            }

            public void onFailure(Throwable caught) {
                Window.alert("Error deleting selected leases");
            }
        });
    }
}

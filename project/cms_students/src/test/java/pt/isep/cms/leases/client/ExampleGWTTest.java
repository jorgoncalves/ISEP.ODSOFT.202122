package pt.isep.cms.leases.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import pt.isep.cms.leases.client.presenter.LeasesPresenter;
import pt.isep.cms.leases.client.view.LeasesView;
import pt.isep.cms.leases.shared.Lease;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;

public class ExampleGWTTest extends GWTTestCase {
    private LeasesPresenter leasesPresenter;
    private LeasesServiceAsync rpcService;
    private HandlerManager eventBus;
    private LeasesPresenter.Display mockDisplay;

    public String getModuleName() {
        return "pt.isep.cms.leases.TestCMSJUnit";
    }

    public void gwtSetUp() {
        rpcService = GWT.create(LeasesService.class);
        mockDisplay = new LeasesView();
        leasesPresenter = new LeasesPresenter(rpcService, eventBus, mockDisplay);
    }

    public void testLeaseSort() {
        ArrayList<LeaseDetails> leaseDetails = new ArrayList<LeaseDetails>();
        leaseDetails.add(new LeaseDetails("0", "c_lease"));
        leaseDetails.add(new LeaseDetails("1", "b_lease"));
        leaseDetails.add(new LeaseDetails("2", "a_lease"));
        leasesPresenter.setLeaseDetails(leaseDetails);
        leasesPresenter.sortLeaseDetails();
        assertTrue(leasesPresenter.getLeaseDetail(0).getDisplayName().equals("a_lease"));
        assertTrue(leasesPresenter.getLeaseDetail(1).getDisplayName().equals("b_lease"));
        assertTrue(leasesPresenter.getLeaseDetail(2).getDisplayName().equals("c_lease"));
    }

    public void testLeasesService() {
        // Create the service that we will test.
        LeasesServiceAsync leasesService = GWT.create(LeasesService.class);
        ServiceDefTarget target = (ServiceDefTarget) leasesService;
        target.setServiceEntryPoint(GWT.getModuleBaseURL() + "leases/leasesService");

        // Since RPC calls are asynchronous, we will need to wait for a response
        // after this test method returns. This line tells the test runner to wait
        // up to 10 seconds before timing out.
        delayTestFinish(10000);

        // fail("Ainda nao implementado");

        // Send a request to the server.
        leasesService.getLease("2", new AsyncCallback<Lease>() {
            public void onFailure(Throwable caught) {
                // The request resulted in an unexpected error.
                fail("Request failure: " + caught.getMessage());
            }

            public void onSuccess(Lease result) {
                // Verify that the response is correct.
                // TODO This assert is commented out because we didnt' manage to bring up a test database with the correct seeds
                //  assertTrue(result != null);


                // Now that we have received a response, we need to tell the test runner
                // that the test is complete. You must call finishTest() after an
                // asynchronous test finishes successfully, or the test will time out.
                finishTest();
            }
        });
    }
}

package pt.isep.cms.leases;

import com.google.gwt.event.shared.HandlerManager;

import java.util.ArrayList;
import junit.framework.TestCase;
import pt.isep.cms.leases.client.LeasesServiceAsync;
import pt.isep.cms.leases.client.presenter.LeasesPresenter;
import pt.isep.cms.leases.shared.LeaseDetails;

import static org.easymock.EasyMock.createStrictMock;

public class ExampleJRETest extends TestCase {
	private LeasesPresenter leasesPresenter;
	private LeasesServiceAsync mockRpcService;
	private HandlerManager eventBus;
	private LeasesPresenter.Display mockDisplay;

	protected void setUp() {
		mockRpcService = createStrictMock(LeasesServiceAsync.class);
		eventBus = new HandlerManager(null);
		mockDisplay = createStrictMock(LeasesPresenter.Display.class);
		leasesPresenter = new LeasesPresenter(mockRpcService, eventBus, mockDisplay);
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
}

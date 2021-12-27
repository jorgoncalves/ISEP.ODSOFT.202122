package pt.isep.cms.leases.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.isep.cms.leases.shared.Lease;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;

public interface LeasesServiceAsync {
    public void addLease(Lease Lease, AsyncCallback<Lease> callback);
    public void deleteLease(String id, AsyncCallback<Boolean> callback);
    public void deleteLeases(ArrayList<String> ids, AsyncCallback<ArrayList<LeaseDetails>> callback);
    public void getLeaseDetails(AsyncCallback<ArrayList<LeaseDetails>> callback);
    public void getLease(String id, AsyncCallback<Lease> callback);
    public void updateLease(Lease Lease, AsyncCallback<Lease> callback);
}

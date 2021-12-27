package pt.isep.cms.leases.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.isep.cms.leases.shared.Lease;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("contactsService")
public interface LeasesService extends RemoteService {

    Lease addLease(Lease contact);
    Boolean deleteLease(String id);
    ArrayList<LeaseDetails> deleteLeases(ArrayList<String> ids);
    ArrayList<LeaseDetails> getLeaseDetails();
    Lease getLease(String id);
    Lease updateLease(Lease contact);
}

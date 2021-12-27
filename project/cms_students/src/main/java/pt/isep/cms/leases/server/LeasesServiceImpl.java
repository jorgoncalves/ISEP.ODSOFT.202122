package pt.isep.cms.leases.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;
import pt.isep.cms.leases.client.LeasesService;
import pt.isep.cms.leases.shared.Lease;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class LeasesServiceImpl extends RemoteServiceServlet implements
        LeasesService {

    private static final String[] leasesOnDateData = new String[] {
            "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
            "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
            "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush",
            "Abigail", "Chad", "Terry", "Bell"};

    private final String[] leasesToDateData = new String[] {
            "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
            "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
            "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard",
            "Louis", "Andrews", "English", "Snedden"};

    private final String[] leasesEmailData = new String[] {
            "mark@example.com", "hollie@example.com", "boticario@example.com",
            "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com",
            "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
            "qetlyxxogg@example.com", "antenas_sul@example.com",
            "cblake@example.com", "gailh@example.com", "orville@example.com",
            "post_master@example.com", "rchilders@example.com", "buster@example.com",
            "user31065@example.com", "ftsgeolbx@example.com"};

    private final HashMap<String, Lease> leases = new HashMap<String, Lease>();

    public LeasesServiceImpl() {
        initLeases();
    }

    private void initLeases() {
        for (int i = 0; i < leasesOnDateData.length && i < leasesToDateData.length; ++i) {
            Lease lease = new Lease(String.valueOf(i), leasesOnDateData[i], leasesToDateData[i], leasesEmailData[i]);
            leases.put(lease.getId(), lease);
        }
    }

    @Override
    public Lease addLease(Lease lease) {
        lease.setId(String.valueOf(leases.size()));
        leases.put(lease.getId(), lease);
        return lease;
    }

    @Override
    public Boolean deleteLease(String id) {
        leases.remove(id);
        return true;
    }

    @Override
    public ArrayList<LeaseDetails> deleteLeases(ArrayList<String> ids) {
        for (int i = 0; i < ids.size(); ++i) {
            deleteLease(ids.get(i));
        }

        return getLeaseDetails();
    }

    @Override
    public ArrayList<LeaseDetails> getLeaseDetails() {
        ArrayList<LeaseDetails> leaseDetails = new ArrayList<LeaseDetails>();

        Iterator<String> it = leases.keySet().iterator();
        while(it.hasNext()) {
            Lease lease = leases.get(it.next());
            leaseDetails.add(lease.getLightWeightContact());
        }

        return leaseDetails;
    }

    @Override
    public Lease getLease(String id) {
        return leases.get(id);
    }

    @Override
    public Lease updateLease(Lease lease) {
        String lid = lease.getId();
        leases.remove(lease.getId());
        leases.put(lease.getId(), lease);
        return lease;
    }
}

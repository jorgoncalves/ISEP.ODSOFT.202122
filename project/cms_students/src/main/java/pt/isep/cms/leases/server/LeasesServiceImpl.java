package pt.isep.cms.leases.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.leases.client.LeasesService;
import pt.isep.cms.leases.shared.Lease;
import pt.isep.cms.leases.shared.LeaseDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class LeasesServiceImpl extends RemoteServiceServlet implements
        LeasesService {

    private static final Date[] leasesOnDateData = new Date[] {
        new Date(2021, 12, 1),
        new Date(2021, 12, 2),
        new Date(2021, 12, 3),
        new Date(2021, 12, 4),
        new Date(2021, 12, 5),
        new Date(2021, 12, 6),
        new Date(2021, 12, 7),
        new Date(2021, 12, 8),
        new Date(2021, 12, 9),
        new Date(2021, 12, 10)  
    };

    private final Date[] leasesToDateData = new Date[] {
        new Date(2021, 12, 8),
        new Date(2021, 12, 9),
        new Date(2021, 12, 10),
        new Date(2021, 12, 11),
        new Date(2021, 12, 12),
        new Date(2021, 12, 13),
        new Date(2021, 12, 14),
        new Date(2021, 12, 15),
        new Date(2021, 12, 16),
        new Date(2021, 12, 17) 
    };

    private final String[] leasesBookData = new String[] {
        "0-3020-4050-1",
        "0-3297-9686-0",
        "0-7162-8596-7",
        "0-9038-4488-5",
        "0-4757-9808-2",
        "0-9370-1872-4",
        "0-3749-7243-5",
        "0-3629-0985-7m",
        "0-5620-1952-9",
        "0-3948-1778-8"
    };

    private final String[] leasesContactsEmailData = new String[]{
            "mark@example.com",
            "hollie@example.com",
            "boticario@example.com",
            "emerson@example.com",
            "healy@example.com",
            "brigitte@example.com",
            "elba@example.com",
            "claudio@example.com",
            "dena@example.com",
            "brasilsp@example.com"
    };

    private final HashMap<String, Lease> leases = new HashMap<String, Lease>();

    public LeasesServiceImpl() {
        initLeases();
    }

    private void initLeases() {
        for (int i = 0; i < leasesOnDateData.length && i < leasesToDateData.length && i < leasesBookData.length && i < leasesContactsEmailData.length; ++i) {
            Lease lease = new Lease(String.valueOf(i), leasesOnDateData[i], leasesToDateData[i], leasesBookData[i], leasesContactsEmailData[i]);
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

    @Override
    public Boolean validLease(Lease leaseToValidate) {
        Boolean valid = true;

        Iterator<String> it = leases.keySet().iterator();

        while (it.hasNext()) {
            Lease l = leases.get(it.next());
            if (leaseToValidate.getBook().equals(l.getBook())) {

                if (!leaseToValidate.getleaseContact().equals(l.getleaseContact())) {

                    if ((leaseToValidate.getOnDate().after(l.getOnDate()) &&
                            leaseToValidate.getOnDate().before(l.getToDate())) ||
                            (leaseToValidate.getToDate().after(l.getOnDate())) &&
                                    leaseToValidate.getToDate().before(l.getToDate())) {

                        valid = false;
                    }
                }
            }
        }

        return valid;
    }
}

package pt.isep.cms.leases.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pt.isep.cms.books.shared.Book;
import pt.isep.cms.contacts.shared.Contact;
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

    /*private final String[] leasesBookData = new String[]{
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
    };*/

    private final HashMap<String, Lease> leases = new HashMap<String, Lease>();

    public LeasesServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        //initPersistentLeases();
    }

    private void initPersistentLeases() {
        Query query = entitymanager.createQuery("Select COUNT(l) from Lease l");
        Long result = (Long) query.getSingleResult();

        //if (result == 0) {
        //    System.out.println("No leases found. Populating db.");
        //    this.entitymanager.getTransaction().begin();

        //    for (int i = 0; i < leasesOnDateData.length && i < leasesToDateData.length /*&& i < leasesBookData.length && i < leasesContactsEmailData.length*/; ++i) {
        //        Lease lease = new Lease(leasesOnDateData[i], leasesToDateData[i], new Book(), new Contact()/*leasesBookData[i], leasesContactsEmailData[i]*/);
        //        this.entitymanager.persist(lease);
        //    }

        //    this.entitymanager.getTransaction().commit();

        //}
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

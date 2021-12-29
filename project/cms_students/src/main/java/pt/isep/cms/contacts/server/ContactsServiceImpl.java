package pt.isep.cms.contacts.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

@SuppressWarnings("serial")
public class ContactsServiceImpl extends RemoteServiceServlet implements
        ContactsService {

    private static final String[] contactsNameData = new String[]{
            "Hollie Voss", "Emerson Milton", "Healy Colette", "Brigitte Cobb", "Elba Lockhart", "Claudio Engle",
            "Dena Pacheco", "Christina Blake", "Gail Horton", "Orville Daniel", "Rae Childers", "Mildred Starnes",
            "Candice Carson", "Louise Kelchner", "Emilio Hutchinson", "Geneva Underwood", "Heriberto Rush",
            "Bulrush Bouchard",
            "Abigail Louis", "Chad Andrews", "Terry English", "Bell Snedden"};

    private final HashMap<String, Contact> contacts = new HashMap<String, Contact>();

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public ContactsServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentContacts();
    }

    private void initPersistentContacts() {
        // TODO: Create a real UID for each contact
        //

        // We only do this if the database is empty...
        Query query = entitymanager.createQuery("Select COUNT(c) from Contact c");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < contactsNameData.length; ++i) {
                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                Contact contact = new Contact(uuidAsString, contactsNameData[i]);
                System.out.println(contact.toString());
                this.entitymanager.persist(contact);
                //contacts.put(contact.getId(), contact);
            }

            System.out.println("Before transaction");
            this.entitymanager.getTransaction().commit();
            System.out.println("After transaction");
        }

    }

    public Contact addContact(Contact contact) {
        //contact.setId(String.valueOf(contacts.size()));

        // Add the new contact to the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Contact updateContact(Contact contact) {

        // Update the contact in the database
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Boolean deleteContact(String id) {
        // Remove the contact in the database
        this.entitymanager.getTransaction().begin();
        Contact contact = entitymanager.find(Contact.class, id);
        entitymanager.remove(contact);
        this.entitymanager.getTransaction().commit();

        return true;
    }

    public ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteContact(ids.get(i));
        }

        return getContactDetails();
    }


    public ArrayList<ContactDetails> getContactDetails() {
        ArrayList<ContactDetails> contactDetails = new ArrayList<ContactDetails>();

        Query query = entitymanager.createQuery("Select c from Contact c");

        @SuppressWarnings("unchecked")
        List<Contact> list = query.getResultList();

        for (Contact contact : list) {
            contactDetails.add(contact.getLightWeightContact());
        }

        return contactDetails;
    }

    public Contact getContact(String id) {
        Contact contact = entitymanager.find(Contact.class, id);

        return contact;
    }
}

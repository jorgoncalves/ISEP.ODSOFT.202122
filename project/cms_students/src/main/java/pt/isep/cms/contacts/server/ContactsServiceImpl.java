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

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public ContactsServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentContacts();
    }

    private void initPersistentContacts() {

        Query query = entitymanager.createQuery("Select COUNT(c) from Contact c");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            System.out.println("No contacts found. Populating db.");
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < contactsNameData.length; ++i) {
                Contact contact = new Contact(contactsNameData[i]);
                contact.setId(String.valueOf(i));
                this.entitymanager.persist(contact);
            }

            this.entitymanager.getTransaction().commit();

        }

    }

    public Contact addContact(Contact contact) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Contact updateContact(Contact contact) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(contact);
        this.entitymanager.getTransaction().commit();

        return contact;
    }

    public Boolean deleteContact(String id) {
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

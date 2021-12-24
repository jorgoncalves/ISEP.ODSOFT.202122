package pt.isep.cms.contacts.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.contacts.client.ContactsService;
import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.contacts.shared.ContactDetails;

@SuppressWarnings("serial")
public class ContactsServiceImpl extends RemoteServiceServlet implements
    ContactsService {

  private static final String[] contactsNameData = new String[] {
      "Hollie Voss", "Emerson Milton", "Healy Colette", "Brigitte Cobb", "Elba Lockhart", "Claudio Engle",
      "Dena Pacheco", "Christina Blake", "Gail Horton", "Orville Daniel", "Rae Childers", "Mildred Starnes",
      "Candice Carson", "Louise Kelchner", "Emilio Hutchinson", "Geneva Underwood", "Heriberto Rush", "Bulrush Bouchard",
      "Abigail Louis", "Chad Andrews", "Terry English", "Bell Snedden"};

  private final HashMap<String, Contact> contacts = new HashMap<String, Contact>();

  public ContactsServiceImpl() {
    initContacts();
  }
  
  private void initContacts() {
    // TODO: Create a real UID for each contact
    //
    for (int i = 0; i < contactsNameData.length; ++i) {
      Contact contact = new Contact(String.valueOf(i), contactsNameData[i]);
      contacts.put(contact.getId(), contact); 
    }
  }
  
  public Contact addContact(Contact contact) {
    contact.setId(String.valueOf(contacts.size()));
    contacts.put(contact.getId(), contact); 
    return contact;
  }

  public Contact updateContact(Contact contact) {
	  String lid=contact.getId();
    contacts.remove(contact.getId());
    contacts.put(contact.getId(), contact); 
    return contact;
  }

  public Boolean deleteContact(String id) {
    contacts.remove(id);
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
    
    Iterator<String> it = contacts.keySet().iterator();
    while(it.hasNext()) { 
      Contact contact = contacts.get(it.next());          
      contactDetails.add(contact.getLightWeightContact());
    }
    
    return contactDetails;
  }

  public Contact getContact(String id) {
    return contacts.get(id);
  }
}

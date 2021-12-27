package pt.isep.cms.leases.shared;

import pt.isep.cms.contacts.shared.ContactDetails;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Lease implements Serializable {

    public String id;
    public String firstName;
    public String lastName;
    public String emailAddress;

    public Lease() {}

    public Lease(String id, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public LeaseDetails getLightWeightContact() {
        return new LeaseDetails(id, getFullName());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public String getFullName() { return firstName + " " + lastName; }
}

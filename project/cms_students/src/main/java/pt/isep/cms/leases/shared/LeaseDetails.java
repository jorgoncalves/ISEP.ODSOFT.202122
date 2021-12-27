package pt.isep.cms.leases.shared;

import pt.isep.cms.contacts.shared.ContactDetails;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LeaseDetails implements Serializable {
    private String id;
    private String displayName;

    public LeaseDetails() {
        new ContactDetails("0", "");
    }

    public LeaseDetails(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}

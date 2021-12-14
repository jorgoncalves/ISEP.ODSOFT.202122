package pt.isep.cms.tags.shared;

import pt.isep.cms.contacts.shared.ContactDetails;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tag implements Serializable {
    public String id;
    public String description;

    public Tag() {
    }

    public Tag(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public TagDetails getLightWeightTag() {
        return new TagDetails(id, getDescription());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

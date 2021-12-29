package pt.isep.cms.tags.shared;

import pt.isep.cms.contacts.shared.ContactDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table
public class Tag implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private String description;

    public Tag() {
    }

    public Tag(String description) {

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

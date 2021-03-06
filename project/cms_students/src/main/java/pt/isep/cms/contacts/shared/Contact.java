package pt.isep.cms.contacts.shared;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table
public class Contact implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private String name;

    public Contact() {
        super();
    }

    public Contact(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Contact [uid=" + id + ", name=" + name + "]";
    }

    public ContactDetails getLightWeightContact() {
        return new ContactDetails(id, getName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

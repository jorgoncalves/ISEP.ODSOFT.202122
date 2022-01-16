package pt.isep.cms.tags.shared;

import javax.persistence.*;
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
        super();
    }

    public Tag(String description) {
        super();
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

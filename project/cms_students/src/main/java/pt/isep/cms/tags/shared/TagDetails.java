package pt.isep.cms.tags.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TagDetails implements Serializable {
    private String id;
    private String displayName;

    public TagDetails() {
        new TagDetails("0", "");
    }

    public TagDetails(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

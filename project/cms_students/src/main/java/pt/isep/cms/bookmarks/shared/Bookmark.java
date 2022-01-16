package pt.isep.cms.bookmarks.shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table
public class Bookmark implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private String note;
    private Date creationDate;

    public Bookmark() {
        super();
    }

    public Bookmark(String note, Date creationDate) {
        super();
        this.note = note;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Bookmark [uid=" + id + ", note=" + note + ", creationDate" + creationDate + "]";
    }

    public BookmarkDetails getLightWeightBookmark() {
        return new BookmarkDetails(id, getNote());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}

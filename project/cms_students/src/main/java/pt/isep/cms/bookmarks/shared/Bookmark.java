package pt.isep.cms.bookmarks.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Bookmark implements Serializable {
    public String id;
    public String note;
    public Date creationDate;

    public Bookmark() {
    }

    public Bookmark(String id, String note, Date creationDate) {
        this.id = id;
        this.note = note;
        this.creationDate = creationDate;
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

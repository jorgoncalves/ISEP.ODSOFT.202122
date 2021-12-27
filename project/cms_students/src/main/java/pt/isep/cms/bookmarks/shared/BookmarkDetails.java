package pt.isep.cms.bookmarks.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookmarkDetails implements Serializable {
  private String id;
  private String displayName;
  
  public BookmarkDetails() {
    new BookmarkDetails("0", "");
  }

  public BookmarkDetails(String id, String displayName) {
    this.id = id;
    this.displayName = displayName;
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getDisplayName() { return displayName; }
  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}

package pt.isep.cms.books.shared;

import pt.isep.cms.bookmarks.shared.Bookmark;
import pt.isep.cms.tags.shared.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table
public class Book implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private String title;
    private String isbn;
    private String author;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Bookmark> bookmarks;

    public Book() {
        super();
    }

    public Book(String title, String author, String isbn, List<Tag> tags, List<Bookmark> bookmarks) {
        super();
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.tags = tags;
        this.bookmarks = bookmarks;
    }

    @Override
    public String toString() {
        StringBuilder strTags = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            strTags.append(tags.get(i).toString());
        }
        return "Book [uid=" + id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + ", tags=" + strTags + "]";
    }


    public BookDetails getLightWeightBook() {
        return new BookDetails(id, getFullName());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTile(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getFullName() {
        StringBuilder strTags = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            strTags.append(tags.get(i).getDescription());
            if (i != tags.size() - 1)
                strTags.append(", ");
        }
        StringBuilder strBookmarks = new StringBuilder();
        for (int i = 0; i < bookmarks.size(); i++) {
            strBookmarks.append(bookmarks.get(i).getNote());
            if (i != bookmarks.size() - 1)
                strBookmarks.append(", ");
        }
        return title + " Author: " + author + "- ISBN: " + isbn + " Tags: " + strTags + " Bookmarks: " + strBookmarks;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
}

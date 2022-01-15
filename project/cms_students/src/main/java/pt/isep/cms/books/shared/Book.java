package pt.isep.cms.books.shared;

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
    @OneToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;

    public Book() {
    }

    public Book(String title, String author, String isbn, List<Tag> tags) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.tags = tags;
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
        return title + " Author: " + author + "- ISBN: " + isbn + " Tags: " + strTags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}

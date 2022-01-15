package pt.isep.cms.books.shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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

    public Book() {
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [uid=" + id + ", title=" + title + ", isbn=" + isbn + ", author=" + author + "]";
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
        return title + " Author: " + author + "- ISBN: " + isbn;
    }
}

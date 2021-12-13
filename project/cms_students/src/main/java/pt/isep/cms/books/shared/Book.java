package pt.isep.cms.books.shared;

import java.io.Serializable;

public class Book implements Serializable {
    public String id;
    public String title;
    public String isbn;


    public Book() {}

    public Book(String id, String title, String isbn) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
    }



    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTile(String title) { this.title = title; }
    public String getISBN() { return isbn; }
    public void setISBN(String isbn) { this.isbn = isbn; }

    public void put(String id, Book book) {
    }
}


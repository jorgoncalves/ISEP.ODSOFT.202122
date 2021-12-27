package pt.isep.cms.books.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {
    public String id;
  public String title;
  public String isbn;
  public String author;
	
	public Book() {}
	
	public Book(String id, String title, String author,String isbn) {
		this.id = id;
    this.title = title;
    this.isbn = isbn;
    this.author = author;
	}
	
	public BookDetails getLightWeightBook() {
	  return new BookDetails(id, getFullName());
	}
	
  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTile(String title) { this.title = title; }

  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }

  public String getISBN() { return isbn; }
  public void setISBN(String isbn) { this.isbn = isbn; }

  public String getFullName() { return title + " Author: " + author + "- ISBN: " + isbn; }
}

package pt.isep.cms.books.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {
	public String id;
  public String firstName;
  public String lastName;
  public String emailAddress;
	
	public Book() {}
	
	public Book(String id, String firstName, String lastName, String emailAddress) {
		this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	public BookDetails getLightWeightBook() {
	  return new BookDetails(id, getFullName());
	}
	
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getEmailAddress() { return emailAddress; }
  public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
  public String getFullName() { return firstName + " " + lastName; }
}

package pt.isep.cms.leases.shared;

import com.google.gwt.user.client.Window;
import pt.isep.cms.books.shared.Book;
import pt.isep.cms.contacts.shared.Contact;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Lease implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;
    private Date onDate;
    private Date toDate;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    private Contact leaseContact;

    public Lease() {}

    public Lease(Date onDate, Date toDate, Book book, Contact contact) {
        this.onDate = onDate;
        this.toDate = toDate;
        this.book = book;
        this.leaseContact = contact;
    }

    public LeaseDetails getLightWeightContact() {
        return new LeaseDetails(id, getLeaseDescription());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Date getOnDate() { return this.onDate; }
    public void setOnDate(Date onDate) { this.onDate = onDate; }
    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public Contact getleaseContact() { return leaseContact; }
    public void setleaseContact(Contact contact) { this.leaseContact = contact; }

    public String getLeaseDescription() {
        return this.book.getTitle() +
            " - Leased from: " + this.onDate.getDate() + "/" + (this.onDate.getMonth()+1) + "/" + (this.onDate.getYear()-1) +
            " to: " + this.toDate.getDate() + "/" + (this.toDate.getMonth()+1) + "/" + (this.toDate.getYear()-1) +
                " By: " + this.leaseContact.getName();
    }
}

package pt.isep.cms.leases.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Lease implements Serializable {

    public String id;
    public Date onDate;
    public Date toDate;
    public String book;

    public Lease() {}

    public Lease(String id, Date onDate, Date toDate, String book) {
        this.id = id;
        this.onDate = onDate;
        this.toDate = toDate;
        this.book = book;
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
    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }

    public String getLeaseDescription() { 
        return this.book + 
            " - Leased from: " + this.onDate.getDate() + "/" + (this.onDate.getMonth()+1) + "/" + (this.onDate.getYear()-1) +
            " to: " + this.toDate.getDate() + "/" + (this.toDate.getMonth()+1) + "/" + (this.toDate.getYear()-1);
    }
}

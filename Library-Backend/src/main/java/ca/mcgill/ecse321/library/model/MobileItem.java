package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class MobileItem extends LibraryItem {
    private String barcode;
    private String author;
    private Date dateOfRelease;
    private float price;
    private Booking booking;

    public MobileItem(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice) {
        super(aItemId, aTitle);
        barcode = aBarcode;
        author = aAuthor;
        dateOfRelease = aDateOfRelease;
        price = aPrice;
    }

    public MobileItem() {
        super();
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String value) {
        this.barcode = value;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    public Date getDateOfRelease() {
        return this.dateOfRelease;
    }

    public void setDateOfRelease(Date value) {
        this.dateOfRelease = value;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float value) {
        this.price = value;
    }

    @ManyToOne(optional = true)
    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}

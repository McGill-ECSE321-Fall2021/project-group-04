package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public abstract class MobileItem extends LibraryItem {
    private String barCode;
    private String title;
    private String author;
    private Date dateOfRelease;
    private float price;
    private Booking booking;

    public MobileItem(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking) {
        super(aItemId);
        barCode = aBarcode;
        title = aTitle;
        author = aAuthor;
        dateOfRelease = aDateOfRelease;
        price = aPrice;
        booking = aBooking;
    }

    public MobileItem() {
        super();
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String value) {
        this.barCode = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
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

    @ManyToOne(optional = false)
    public Booking getBooking() {
        return this.booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

}

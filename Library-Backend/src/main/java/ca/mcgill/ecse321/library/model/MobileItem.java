import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import java.sql.Date;

import javax.persistence.*;

@Entity
public abstract class MobileItem extends LibraryItem{
private String barCode;

public MobileItem(String aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking)
{
  super(aItemId);
  title = aTitle;
  author = aAuthor;
  dateOfRelease = aDateOfRelease;
  price = aPrice;
  
}
   
   public void setBarCode(String value) {
this.barCode = value;
    }
public String getBarCode() {
return this.barCode;
    }
private String title;

public void setTitle(String value) {
this.title = value;
    }
public String getTitle() {
return this.title;
    }
private String author;

public void setAuthor(String value) {
this.author = value;
    }
public String getAuthor() {
return this.author;
    }
private Date dateOfRelease;

public void setDateOfRelease(Date value) {
this.dateOfRelease = value;
    }
public Date getDateOfRelease() {
return this.dateOfRelease;
    }
private float price;

public void setPrice(float value) {
this.price = value;
    }
public float getPrice() {
return this.price;
    }
private Booking booking;

@ManyToOne(optional=false)
public Booking getBooking() {
   return this.booking;
}

public void setBooking(Booking booking) {
   this.booking = booking;
}

}

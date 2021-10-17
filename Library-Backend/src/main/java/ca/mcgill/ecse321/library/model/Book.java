import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;
@Entity
public class Book extends MobileItem{
private String isbn;

public Book(String aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, String aIsbn, int aNumberOfPages)
{
  super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
  isbn = aIsbn;
  numberOfPages = aNumberOfPages;
}
   

public void setIsbn(String value) {
this.isbn = value;
    }

@Id
public String getIsbn() {
return this.isbn;
    }
private int numberOfPages;

public void setNumberOfPages(int value) {
this.numberOfPages = value;
    }
public int getNumberOfPages() {
return this.numberOfPages;
       }
   }

package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Book extends MobileItem {
    private String isbn;
    private int numberOfPages;


    public Book(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, String aIsbn, int aNumberOfPages) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
        isbn = aIsbn;
        numberOfPages = aNumberOfPages;
    }

    public Book() {
        super();
    }

    @Id
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String value) {
        this.isbn = value;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public void setNumberOfPages(int value) {
        this.numberOfPages = value;
    }
}

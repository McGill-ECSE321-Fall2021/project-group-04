package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Movie extends MobileItem {
    private float length;

    public Movie(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, float aLength) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
        length = aLength;
    }

    public Movie() {
        super();
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float value) {
        this.length = value;
    }
}

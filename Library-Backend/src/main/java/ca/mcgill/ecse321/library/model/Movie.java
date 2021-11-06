package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public class Movie extends MobileItem {
    private float length;

    public Movie(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, float aLength) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice);
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

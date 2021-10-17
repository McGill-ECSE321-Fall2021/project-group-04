import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Movie extends MobileItem{
private float length;

	public Movie(String aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, float aLength)
	{
	  super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
	  length = aLength;
	}
   
   public void setLength(float value) {
this.length = value;
    }
public float getLength() {
return this.length;
       }
   }

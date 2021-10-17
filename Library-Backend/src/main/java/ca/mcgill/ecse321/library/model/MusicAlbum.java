import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class MusicAlbum extends MobileItem{
private int numberOfSongs;


public MusicAlbum(String aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, int aNumberOfSongs, float aTotalLength)
{
  super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
  numberOfSongs = aNumberOfSongs;
  totalLength = aTotalLength;
}
   
   public void setNumberOfSongs(int value) {
this.numberOfSongs = value;
    }
public int getNumberOfSongs() {
return this.numberOfSongs;
    }
private float totalLength;

public void setTotalLength(float value) {
this.totalLength = value;
    }
public float getTotalLength() {
return this.totalLength;
       }
   }

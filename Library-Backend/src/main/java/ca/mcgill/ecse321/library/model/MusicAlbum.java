package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class MusicAlbum extends MobileItem {
    private int numberOfSongs;
    private float totalLength;

    public MusicAlbum(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, Booking aBooking, int aNumberOfSongs, float aTotalLength) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, aBooking);
        numberOfSongs = aNumberOfSongs;
        totalLength = aTotalLength;
    }

    public MusicAlbum() {
        super();
    }

    public int getNumberOfSongs() {
        return this.numberOfSongs;
    }

    public void setNumberOfSongs(int value) {
        this.numberOfSongs = value;
    }

    public float getTotalLength() {
        return this.totalLength;
    }

    public void setTotalLength(float value) {
        this.totalLength = value;
    }
}

package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class MusicAlbumDto extends MobileItemDto {

    private int numberOfSongs;
    private float totalLength;

    public MusicAlbumDto(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, int aNumberOfSongs, float aTotalLength, BookingDto booking) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, booking);
        numberOfSongs = aNumberOfSongs;
        totalLength = aTotalLength;
    }

    public int getNumberOfSongs() {
        return this.numberOfSongs;
    }

    public float getTotalLength() {
        return this.totalLength;
    }

}

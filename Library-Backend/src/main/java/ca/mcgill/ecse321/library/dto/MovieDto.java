package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class MovieDto extends MobileItemDto {

    private final float length;

    public MovieDto(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, float aLength, BookingDto booking) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, booking);
        length = aLength;
    }

    public float getLength() {
        return this.length;
    }

}

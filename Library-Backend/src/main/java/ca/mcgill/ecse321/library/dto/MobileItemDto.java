package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public abstract class MobileItemDto extends LibraryItemDto {

    private final String barcode;
    private final String author;
    private final Date dateOfRelease;
    private final float price;
    private final BookingDto booking;

    public MobileItemDto(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, BookingDto booking) {
        super(aItemId, aTitle);
        barcode = aBarcode;
        author = aAuthor;
        dateOfRelease = aDateOfRelease;
        price = aPrice;
        this.booking = booking;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public float getPrice() {
        return price;
    }

    public BookingDto getBooking() {
        return booking;
    }
}

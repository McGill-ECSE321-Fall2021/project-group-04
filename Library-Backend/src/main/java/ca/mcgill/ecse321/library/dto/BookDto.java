package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class BookDto extends MobileItemDto {

    private final String isbn;
    private final int numberOfPages;

    public BookDto(Long aItemId, String aBarcode, String aTitle, String aAuthor, Date aDateOfRelease, float aPrice, String aIsbn, int aNumberOfPages, BookingDto booking) {
        super(aItemId, aBarcode, aTitle, aAuthor, aDateOfRelease, aPrice, booking);
        isbn = aIsbn;
        numberOfPages = aNumberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}

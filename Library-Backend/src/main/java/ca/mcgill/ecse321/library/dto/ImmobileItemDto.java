package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public abstract class ImmobileItemDto extends LibraryItemDto {

    private Date date;
    private int numberOfPages;

    public ImmobileItemDto(Long aItemId, String aTitle, Date aDate, int aNumberOfPages) {
        super(aItemId, aTitle);
        date = aDate;
        numberOfPages = aNumberOfPages;
    }

    public Date getDate() {
        return date;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}

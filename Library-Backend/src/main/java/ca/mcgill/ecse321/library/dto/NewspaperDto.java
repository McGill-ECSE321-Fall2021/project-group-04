package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class NewspaperDto extends ImmobileItemDto {

    public NewspaperDto(Long aItemId, String aTitle, Date aDate, int aNumberOfPages) {
        super(aItemId, aTitle, aDate, aNumberOfPages);
    }

}

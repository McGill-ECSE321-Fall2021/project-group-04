package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class ArchiveDto extends ImmobileItemDto {

    public ArchiveDto(Long aItemId, String aTitle, Date aDate, int aNumberOfPages) {
        super(aItemId, aTitle, aDate, aNumberOfPages);
    }

}

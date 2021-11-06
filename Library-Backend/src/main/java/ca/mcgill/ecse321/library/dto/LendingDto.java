package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class LendingDto extends BookingTypeDto {
    private Date returnDate;

    public LendingDto(Long id, Date aReturnDate) {
        super(id);
        returnDate = aReturnDate;
    }

    public Long getId() {
        return super.getId();
    }

    public Date getReturnDate() {
        return this.returnDate;
    }



}

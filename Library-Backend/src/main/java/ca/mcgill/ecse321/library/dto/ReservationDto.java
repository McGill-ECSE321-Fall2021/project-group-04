package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class ReservationDto extends BookingTypeDto {
    private Date expirationDate;

    public ReservationDto(Long id, Date aExpirationDate) {
        super(id);
        expirationDate = aExpirationDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public Long getId() {
        return super.getId();
    }
}

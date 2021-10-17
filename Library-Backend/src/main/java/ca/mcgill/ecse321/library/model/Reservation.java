package ca.mcgill.ecse321.library.model;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class Reservation extends BookingType {
    private Date expirationDate;

    public Reservation(Long id, Date aExpirationDate) {
        super(id);
        expirationDate = aExpirationDate;
    }

    public Reservation() {
        super();
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date value) {
        this.expirationDate = value;
    }
}

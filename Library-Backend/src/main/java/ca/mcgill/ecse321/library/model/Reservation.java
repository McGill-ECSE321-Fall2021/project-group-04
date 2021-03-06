package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

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

    public Long getId() {
        return super.getId();
    }
}

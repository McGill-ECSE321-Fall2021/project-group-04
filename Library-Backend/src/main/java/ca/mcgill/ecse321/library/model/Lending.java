package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public class Lending extends BookingType {
    private Date returnDate;

    public Lending(Long id, Date aReturnDate) {
        super(id);
        returnDate = aReturnDate;
    }

    public Lending() {
        super();
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}

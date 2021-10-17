import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Lending extends BookingType{
private Date returnDate;

public Lending(Date aReturnDate, String id)
{
  super(id);
  returnDate = aReturnDate;
}

@OneToOne(optional=false)
public Date getReturnDate() {
   return this.returnDate;
}

public void setReturnDate(Date returnDate) {
   this.returnDate = returnDate;
}

}

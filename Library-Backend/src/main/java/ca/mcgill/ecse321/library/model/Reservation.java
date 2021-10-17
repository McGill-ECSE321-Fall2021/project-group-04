import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Reservation extends BookingType{
private Date expirationDate;

public Reservation(Date aExpirationDate, String id)
{
  super(id);
  expirationDate = aExpirationDate;
}
   
   public void setExpirationDate(Date value) {
this.expirationDate = value;
    }
public Date getExpirationDate() {
return this.expirationDate;
       }
   }

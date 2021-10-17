import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Booking{
private BookingType bookingType;

public Booking(String aBookingId, Date aBookingDate, User aUser)
{
  bookingDate = aBookingDate;
  
}

@OneToOne
public BookingType getBookingType() {
   return this.bookingType;
}

public void setBookingType(BookingType bookingType) {
   this.bookingType = bookingType;
}

private String bookingId;

public void setBookingId(String value) {
this.bookingId = value;
    }

@Id
public String getBookingId() {
return this.bookingId;
    }
private Date bookingDate;

@OneToOne(optional=false)
public Date getBookingDate() {
   return this.bookingDate;
}

public void setBookingDate(Date bookingDate) {
   this.bookingDate = bookingDate;
}

private User user;

@ManyToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

}

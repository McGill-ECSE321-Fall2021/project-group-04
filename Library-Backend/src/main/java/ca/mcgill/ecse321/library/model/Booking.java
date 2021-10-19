package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bookings")
public class Booking {
    private BookingType bookingType;
    private Long id;
    private Date bookingDate;
    private User user;


    public Booking(Long aBookingId, Date aBookingDate, User aUser, BookingType aBookingType) {
        id = aBookingId;
        bookingDate = aBookingDate;
        user = aUser;
        bookingType = aBookingType;
    }

    public Booking() {
        super();
    }

    @OneToOne(optional = false)
    public BookingType getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return this.id;
    }

    public void setId(Long aId) {
        this.id = aId;
    }

    public Date getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @ManyToOne(optional = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

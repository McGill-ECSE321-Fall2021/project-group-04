package ca.mcgill.ecse321.library.dto;

import java.sql.Date;

public class BookingDto {
    private BookingTypeDto bookingType;
    private Long id;
    private Date bookingDate;
    private UserDto user;


    public BookingDto(Long aBookingId, Date aBookingDate, UserDto aUser, BookingTypeDto aBookingType) {
        id = aBookingId;
        bookingDate = aBookingDate;
        user = aUser;
        bookingType = aBookingType;
    }


    public BookingTypeDto getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(BookingTypeDto bookingType) {
        this.bookingType = bookingType;
    }

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

    public UserDto getUser() {
        return this.user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

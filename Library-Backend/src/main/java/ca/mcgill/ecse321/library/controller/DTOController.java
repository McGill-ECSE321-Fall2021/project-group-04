package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.model.*;

public class DTOController {
    public static BookingTypeDto convertToDto(BookingType bt){
        //need to differentiate between lending and reservation
        if(bt instanceof Lending){
            return new LendingDto(bt.getId(),((Lending) bt).getReturnDate()); //return LendingDto
        }

        else{
            return new ReservationDto(bt.getId(),((Reservation)bt).getExpirationDate()); //return ReservationDto
        }

    }

    public static UserDto convertToDto(User user){
        //not taking into consideration different types of users
        return new UserDto(user.getId(),user.getUsername(), user.getPassword(), user.getAddress());
    }

    public static BookingDto convertToDto(Booking b, User aUser, BookingType bt){
        BookingTypeDto btDto = convertToDto(bt);
        UserDto userDto = convertToDto(aUser);

        return new BookingDto(b.getId(),b.getBookingDate(),userDto,btDto);
    }

//    public static BookingDto convertToDto(Book book){
//        BookDto bDto =
//    }
}

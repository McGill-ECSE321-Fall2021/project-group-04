package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.ArchiveService;
import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.UserService;
import jdk.incubator.vector.VectorOperators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class LibraryRestController {

    //@Autowired
    private BookingService bookingService;
    private UserService userService;


    @GetMapping(value = { "/bookings", "/bookings/" })
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream().map(bk -> convertToDto(bk,bk.getUser(),bk.getBookingType())).collect(Collectors.toList());
    }

    @PostMapping(value = { "/booking/{name}/itemType/{itemType}/itemId/{itemId}", "/booking/{name}/itemType/{itemType}/itemId/{itemId}/" })
    public BookingDto createPerson(@PathVariable("name") String name,@PathVariable("itemType") String itemType, @PathVariable("itemId") String itemId) throws IllegalArgumentException {
        Booking booking = bookingService.createBooking(name,itemType, itemId);
        return convertToDto(booking, booking.getUser(), booking.getBookingType());
    }

    @PostMapping(value = {"/login", "/login/"})
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        User user = null;
        try {
            user = userService.login(username, password);
        }
        catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(user instanceof Member) {
            return new ResponseEntity<>(convertToDto((Member) user), HttpStatus.OK);
        }

        if(user instanceof Librarian) {
            return new ResponseEntity<>(convertToDto((Librarian) user), HttpStatus.OK);
        }

        if(user instanceof HeadLibrarian) {
            return new ResponseEntity<>(convertToDto((HeadLibrarian) user), HttpStatus.OK);
        }

        return null;
    }


    private BookingTypeDto convertToDto(BookingType bt){

        //need to differentiate between lending and reservation

        if(bt instanceof Lending){
            return new LendingDto(bt.getId(),((Lending) bt).getReturnDate()); //return LendingDto
        }

        else{
            return new ReservationDto(bt.getId(),((Reservation)bt).getExpirationDate()); //return ReservationDto
        }

    }

    private static UserDto convertToDto(User user){
        return new UserDto(user.getId(),user.getUsername(), user.getPassword(), user.getAddress());
    }

    private BookingDto convertToDto(Booking b, User aUser, BookingType bt){
        BookingTypeDto btDto = convertToDto(bt);
        UserDto userDto = convertToDto(aUser);

        return new BookingDto(b.getId(),b.getBookingDate(),userDto,btDto);
    }


}

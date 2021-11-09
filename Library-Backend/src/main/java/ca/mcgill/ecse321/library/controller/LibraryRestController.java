package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.ArchiveService;
import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.MemberService;
import ca.mcgill.ecse321.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class LibraryRestController {


    private BookingService bookingService;
    private UserService userService;
    private MemberService memberService;


    @GetMapping(value = { "/bookings", "/bookings/" })
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream().map(bk -> DTOController.convertToDto(bk,bk.getUser(),bk.getBookingType())).collect(Collectors.toList());
    }

    @PostMapping(value = { "/booking/{name}/itemType/{itemType}/itemId/{itemId}", "/booking/{name}/itemType/{itemType}/itemId/{itemId}/" })
    public BookingDto createPerson(@PathVariable("name") String name,@PathVariable("itemType") String itemType, @PathVariable("itemId") String itemId) throws IllegalArgumentException {
        Booking booking = bookingService.createBooking(name,itemType, itemId);
        return DTOController.convertToDto(booking, booking.getUser(), booking.getBookingType());
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

    @PostMapping(value = {"/signup_user/", "/signup_user"})
    public ResponseEntity<?> signupUser(@RequestParam String address, @RequestParam String username, @RequestParam String password,
                                        @RequestParam Member.MemberType memberType, @RequestParam Member.MemberStatus memberStatus) {

        Member member = null;
        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            memberService.deleteMember(username);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(convertToDto(member), HttpStatus.CREATED);
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

    private static UserDto convertToDto(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User is not found.");
        }
        return new UserDto(user.getId(),user.getUsername(), user.getPassword(), user.getAddress());
    }

    private BookingDto convertToDto(Booking b, User aUser, BookingType bt){
        BookingTypeDto btDto = convertToDto(bt);
        UserDto userDto = convertToDto(aUser);

        return new BookingDto(b.getId(),b.getBookingDate(),userDto,btDto);
    }



}

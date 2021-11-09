package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.MemberService;
import ca.mcgill.ecse321.library.service.UserService;
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

    //will remove this later (copy available in bookingController)
    @GetMapping(value = { "/bookings", "/bookings/" })
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings().stream().map(bk -> DTOConverter.convertToDto(bk,bk.getUser(),bk.getBookingType())).collect(Collectors.toList());
    }

    //will remove this later (copy available in bookingController)
    @PostMapping(value = { "/booking/{name}/itemType/{itemType}/itemId/{itemId}", "/booking/{name}/itemType/{itemType}/itemId/{itemId}/" })
    public BookingDto createBooking(@PathVariable("name") String name,@PathVariable("itemType") String itemType, @PathVariable("itemId") String itemId) throws IllegalArgumentException {
        Booking booking = bookingService.createBooking(name,itemType, itemId);
        return DTOConverter.convertToDto(booking, booking.getUser(), booking.getBookingType());
    }

    //move this to MemberController
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

        return new ResponseEntity<>(DTOConverter.convertToDto(member), HttpStatus.CREATED);
    }

}

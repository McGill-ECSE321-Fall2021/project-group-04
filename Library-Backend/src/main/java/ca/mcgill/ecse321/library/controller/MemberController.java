package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.BookingDto;
import ca.mcgill.ecse321.library.dto.MemberDto;
import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    /**
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @GetMapping(value = {"/member/{username}"})
    public ResponseEntity<?> viewMember(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(DTOConverter.convertToDto(memberService.getMember(username)), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * @param username
     * @return
     * @author alymo
     */
    @GetMapping(value = {"/member_reservations/{username}"})
    public List<BookingDto> viewMemberBookings(@PathVariable("username") String username) {
            List<Booking> bookings = memberService.getMemberBookings(username);
            List<BookingDto> bookingDtos = new ArrayList<BookingDto>();

            for (Booking b : bookings ) {

                if(b.getBookingType() instanceof  Reservation) {
                    bookingDtos.add((BookingDto) DTOConverter.convertToDto(b));
                }
            }
            return  bookingDtos;
    }

    /**
     * @param username
     * @return
     * @author Simo
     */
    @GetMapping(value = {"/member_bookReservations/{username}"})
    public List<BookDto> viewMemberBookReservations(@PathVariable("username") String username) {
        List<Booking> bookings = memberService.getMemberBookings(username);
        List<BookDto> bookDtos = new ArrayList<BookDto>();

        for (Booking b : bookings){

            if (b.getBookingType() instanceof Reservation) {
                List<Book> books = bookService.getBooksByBooking(b);

                for (Book book : books){
                    bookDtos.add((BookDto) DTOConverter.convertToDto(book,b));
                }
            }
        }
        return  bookDtos;
    }

    /**
     * @param username
     * @return
     * @author Simo
     */
    @GetMapping(value = {"/member_bookLendings/{username}"})
    public List<BookDto> viewMemberBookLendings(@PathVariable("username") String username) {
        List<Booking> bookings = memberService.getMemberBookings(username);
        List<BookDto> bookDtos = new ArrayList<BookDto>();

        for (Booking b : bookings){

            if (b.getBookingType() instanceof Lending) {
                List<Book> books = bookService.getBooksByBooking(b);

                for (Book book : books){
                    bookDtos.add((BookDto) DTOConverter.convertToDto(book,b));
                }
            }
        }
        return  bookDtos;
    }

    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @param aMemberType
     * @param aMemberStatus
     * @return
     * @author Jewoo Lee
     */
    @PostMapping(value = {"/member_sign_up/", "/member_sign_up"})
    public ResponseEntity<?> createMember(@RequestParam("username") String aUsername,
                                          @RequestParam("password") String aPassword,
                                          @RequestParam("address") String aAddress,
                                          @RequestParam("member_type") String aMemberType,
                                          @RequestParam("member_status") String aMemberStatus) {

        try {
            Member member = memberService.createMember(aUsername, aPassword, aAddress, Member.MemberType.valueOf(aMemberType), Member.MemberStatus.valueOf(aMemberStatus));
            return new ResponseEntity<>(DTOConverter.convertToDto(member), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @GetMapping(value = {"/members"})
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }

    @PostMapping(value = {"/member_login", "/member_login/"})
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Member member = null;
        try {
            member = memberService.login(username, password);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(DTOConverter.convertToDto(member), HttpStatus.OK);
    }

}

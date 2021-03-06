package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.BookingDto;
import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.MobileItem;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.BookingService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookService bookService;


    @GetMapping(value = {"/bookings", "/bookings/"})
    public ResponseEntity<?> getAllBookings() {
        try {
            List<BookingDto> bookings = bookingService.getAllBookings().stream().map(bk -> DTOConverter.convertToDto(bk, bk.getUser(), bk.getBookingType())).collect(Collectors.toList());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/reservations", "/reservations/"})
    public ResponseEntity<?> getAllReservations() {
        try {
            List<BookDto> bookings = bookService.getAllBooks().stream().filter(c -> c.getBooking() != null && c.getBooking().getBookingType() instanceof Reservation).map(bk -> DTOConverter.convertToDto(bk,bk.getBooking())).collect(Collectors.toList());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/lendings", "/lendings/"})
    public ResponseEntity<?> getAllLendings() {
        try {
            List<BookDto> bookings = bookService.getAllBooks().stream().filter(c -> c.getBooking() != null && c.getBooking().getBookingType() instanceof Lending).map(bk -> DTOConverter.convertToDto(bk,bk.getBooking())).collect(Collectors.toList());
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = {"/booking/{name}/itemType/{itemType}/itemId/{itemId}", "/booking/{name}/itemType/{itemType}/itemId/{itemId}/"})
    public ResponseEntity<?> createBooking(@PathVariable String name, @PathVariable String itemType, @PathVariable String itemId) {
        try {
            Booking booking = bookingService.createBooking(name, itemType, itemId);
            return new ResponseEntity<>(DTOConverter.convertToDto(booking, booking.getUser(), booking.getBookingType()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/return/itemType/{itemType}/itemId/{itemId}", "/return/{name}/itemType/{itemType}/itemId/{itemId}/"})
    public ResponseEntity<?> returnItem(@PathVariable String itemType, @PathVariable String itemId) {
        try {
            MobileItem item = bookingService.returnLibraryItem(itemType, itemId);
            return new ResponseEntity<>("Successfully returned item", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/checkout/username/{username}/bookingId/{bookingId}", "/checkout/username/{username}/bookingId/{bookingId}/"})
    public ResponseEntity<?> checkoutItem(@PathVariable String username, @PathVariable String bookingId) {
        try {
            Booking booking = bookingService.confirmBooking(username, bookingId);
            return new ResponseEntity<>(DTOConverter.convertToDto(booking, booking.getUser(), booking.getBookingType()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

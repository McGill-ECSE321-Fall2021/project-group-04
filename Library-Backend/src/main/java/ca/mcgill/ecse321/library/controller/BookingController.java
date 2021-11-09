package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
public class BookingController {

    @Autowired
    BookingService bookingService;

//    @PostMapping(value = { "/create_booking/" })
//    public ResponseEntity<?> createBooking(@RequestParam String username, @RequestParam String elementType,
//                                           @RequestParam String elementId){
//
//    }
}

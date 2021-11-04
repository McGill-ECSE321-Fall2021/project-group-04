package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Date;


public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MusicAlbumRepository musicAlbumRepository;

    @Autowired
    ArchiveRepository archiveRepository;

    @Autowired
    NewspaperRepository newspaperRepository;

    @Autowired
    MemberRepository memberRepository;


    @Transactional
    public Booking createBooking(String username, String elementType, String elementId, String endDate){

        return bookingChecks(username, elementType, elementId, endDate);
    }

    private Booking bookingChecks(String username, String elementType, String elementId, String endDate){
        String error = "";

        if(username == null || username == "") error += "Username cannot be empty ";
        if(elementType == null || elementType == "") error += "elementType cannot be empty ";
        if(elementId == null || elementId == "") error += "elementId cannot be empty ";
        if(endDate == null || endDate == "") error += "endDate cannot be empty ";

        try {
            Date date = Date.valueOf(endDate);
        }
        catch (Exception e){
            error += "date format is not correct ";
        }

        Member customer;

        if(memberRepository.existsMemberByUsername(username))
            customer = memberRepository.findMemberByUsername(username);
        else {
            error += "could not find a customer with that username ";
            throw new IllegalArgumentException(error);
        }


        LibraryItem item;
        Reservation reservation = new Reservation();
        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        //expiry date is 3 days after current date
        reservation.setExpirationDate(new java.sql.Date(System.currentTimeMillis() + MILLIS_IN_A_DAY * 3));
        Booking booking = new Booking();
        booking.setBookingType(reservation);
        booking.setUser(customer);



        booking.setBookingDate(new java.sql.Date(System.currentTimeMillis()));

        switch (elementType){
            default: error += "invalid element type ";
            case "Book":
                try {
                    item = (Book) bookRepository.findBookByTitle(elementId);
                    if(item == null) error += "could not find a book with that name ";
                    Book book = (Book) item;
                    book.setBooking(booking);

                }
                catch (Exception e){
                    error += "could not find a book with that name ";
                }
                break;
            case "Movie":
                try {
                    item = (Movie) movieRepository.findMovieByTitle(elementId);
                    if(item == null){
                        error += "could not find a movie with that name ";
                        throw new IllegalArgumentException(error);
                    }
                    Movie movie = (Movie) item;
                    movie.setBooking(booking);
                }
                catch (Exception e){
                    error += "could not find a book with that name ";
                }
                break;
            case "MusicAlbum":
                try {
                    item = (MusicAlbum) musicAlbumRepository.findMusicAlbumByTitle(elementId);
                    if(item == null) {
                        error += "could not find a music album with that name ";
                        throw new IllegalArgumentException(error);
                    }

                    MusicAlbum album = (MusicAlbum) item;
                    album.setBooking(booking);
                }
                catch (Exception e){
                    error += "could not find a music album with that name ";
                }
                break;
        }



        if(error != "") throw new IllegalArgumentException(error);
        else return booking;
    }

    
}

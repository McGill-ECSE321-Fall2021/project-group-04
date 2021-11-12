package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MusicAlbumRepository musicAlbumRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public List<Booking> getAllBookings(){
        return toList(bookingRepository.findAll());
    }

    /**
     * gets the mobile item from the db then sets its booking to null and then saves it again
     * @author Simo Benkirane
     * @param itemType
     * @param itemTitle
     */
    @Transactional
    public MobileItem returnLibraryItem(String itemType, String itemTitle){

        //possible future issue
        //bookings might still point to the user even though no item is pointing to them
        MobileItem item = getMobileItem(itemType,itemTitle);

        if(item.getBooking() == null) {
            throw new IllegalArgumentException("Book already returned");
        }

        item.setBooking(null);
        return saveMobileItem(itemType,item);

    }

    /**
     * saves mobile item into db
     * @author Simo Benkirane
     * @param elementType
     * @param item
     * @return
     */
    public MobileItem saveMobileItem(String elementType, MobileItem item){

        String error = "";
        //MobileItem item = null;
        switch (elementType){
            default: error += "invalid element type "; break;
            case "Book":
                try {
                    if(item == null) {
                        throw new IllegalArgumentException();
                    }
                    return bookRepository.save((Book)item);

                }
                catch (Exception e){
                    error += "could not save book ";
                }
                break;
            case "Movie":
                try {
                    if(item == null){
                        throw new IllegalArgumentException();
                    }
                    return movieRepository.save((Movie) item);
                }
                catch (Exception e){
                    error += "could not save movie ";
                }
                break;
            case "MusicAlbum":
                try {
                    if(item == null) {
                        throw new IllegalArgumentException();
                    }
                    return musicAlbumRepository.save((MusicAlbum) item);
                }
                catch (Exception e){
                    error += "could not save music album ";
                }
                break;
        }

        if(!error.equals("")) throw new IllegalArgumentException(error);

        else return item;


    }

    /**
     * gets mobile item from db
     * @author Simo Benkirane
     * @param elementType
     * @param elementId
     * @return
     */
    public MobileItem getMobileItem(String elementType, String elementId){

        String error = "";
        MobileItem item = null;
        switch (elementType){
            default: error += "invalid element type "; break;
            case "Book":
                try {
                    item = (Book) bookRepository.findBookByTitle(elementId);
                    if(item == null) throw new IllegalArgumentException();
                }
                catch (Exception e){
                    error += "could not find a book with that name ";
                }
                break;
            case "Movie":
                try {
                    item = (Movie) movieRepository.findMovieByTitle(elementId);
                    if(item == null){
                        throw new IllegalArgumentException();
                    }
                }
                catch (Exception e){
                    error += "could not find a movie with that name ";
                }
                break;
            case "MusicAlbum":
                try {
                    item = (MusicAlbum) musicAlbumRepository.findMusicAlbumByTitle(elementId);
                    if(item == null) {
                        throw new IllegalArgumentException();
                    }
                }
                catch (Exception e){
                    error += "could not find a music album with that name ";
                }
                break;
        }

        if(!error.equals("")) throw new IllegalArgumentException(error);

        else return item;


    }


    @Transactional
    public Booking createBooking(String username, String elementType, String elementId){
        return bookingChecks(username, elementType, elementId);
    }

    @Transactional
    public Booking confirmBooking(String username, String bookingId){
        firstChecks(username, "not imp", bookingId);

        if(!bookingRepository.existsBookingById(Long.valueOf(bookingId))){
            throw new IllegalArgumentException("There is no booking with the entered id");
        }

        Booking booking = bookingRepository.findBookingById(Long.valueOf(bookingId));

        if (!booking.getUser().getUsername().equals(username)){
            throw new IllegalArgumentException("The customer who has made this booking shall be the one confirming it");
        }

        Lending lending = new Lending();
        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        //return date is 3- days after current date
        lending.setReturnDate(new java.sql.Date(System.currentTimeMillis() + MILLIS_IN_A_DAY * 30));
        booking.setBookingType(lending);

        bookingRepository.save(booking);
        return booking;
    }

    private Booking bookingChecks(String username, String elementType, String elementId){

        firstChecks(username, elementType, elementId);
        Member customer = getCustomer(username);
        Booking booking = makeBooking(customer);
        setType(booking, elementType, elementId);

        bookingRepository.save(booking);

        return booking;
    }

    private void firstChecks(String username, String elementType, String elementId){
        String error = "";

        if(username == null || username == "") error += "Username cannot be empty ";
        if(elementType == null || elementType == "") error += "elementType cannot be empty ";
        if(elementId == null || elementId == "") error += "elementId cannot be empty ";


        if(!error.equals("")) throw new IllegalArgumentException(error);
    }

    private Member getCustomer(String username){

        Member customer;
        String error = "";

        if(memberRepository.existsMemberByUsername(username)) {
            customer = memberRepository.findMemberByUsername(username);
            return customer;
        }
        else {
            error += "could not find a customer with that username ";
            throw new IllegalArgumentException(error);
        }

    }

    private Booking makeBooking (Member customer){
        LibraryItem item;
        Reservation reservation = new Reservation();
        long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        //expiry date is 3 days after current date
        reservation.setExpirationDate(new java.sql.Date(System.currentTimeMillis() + MILLIS_IN_A_DAY * 3));
        reservationRepository.save(reservation);

        Booking booking = new Booking();
        booking.setBookingType(reservation);
        booking.setUser(customer);

        booking.setBookingDate(new java.sql.Date(System.currentTimeMillis()));

        return booking;
    }

    private void setType(Booking booking, String elementType, String elementId){
        String error = "";
        LibraryItem item;
        switch (elementType){
            default: error += "invalid element type "; break;
            case "Book":
                try {
                    item = (Book) bookRepository.findBookByTitle(elementId);
                    if(item == null) throw new IllegalArgumentException();
                    Book book = (Book) item;
                    book.setBooking(booking);
                    bookRepository.save(book);

                }
                catch (Exception e){
                    error += "could not find a book with that name ";
                }
                break;
            case "Movie":
                try {
                    item = (Movie) movieRepository.findMovieByTitle(elementId);
                    if(item == null){
                        throw new IllegalArgumentException();
                    }
                    Movie movie = (Movie) item;
                    movie.setBooking(booking);
                    movieRepository.save(movie);
                }
                catch (Exception e){
                    error += "could not find a movie with that name ";
                }
                break;
            case "MusicAlbum":
                try {
                    item = (MusicAlbum) musicAlbumRepository.findMusicAlbumByTitle(elementId);
                    if(item == null) {
                        throw new IllegalArgumentException();
                    }

                    MusicAlbum album = (MusicAlbum) item;
                    album.setBooking(booking);
                    musicAlbumRepository.save(album);
                }
                catch (Exception e){
                    error += "could not find a music album with that name ";
                }
                break;
        }

        if(!error.equals("")) throw new IllegalArgumentException(error);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }



}

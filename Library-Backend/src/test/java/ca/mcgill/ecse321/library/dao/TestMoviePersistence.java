package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMoviePersistence {
    @Autowired
    EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LendingRepository lendingRepository;

    @AfterEach
    public void clearDatabase() {
        movieRepository.deleteAll();
        bookingRepository.deleteAll();
        lendingRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadMovie() {

        //Configure User------------->
        Member tUser = new Member();
        tUser.setUsername("Simo4");
        tUser.setPassword("12341234");
        tUser.setAddress("123 street");



        Lending bT = new Lending();

        Booking booking1= new Booking();
        booking1.setBookingDate(Date.valueOf("2015-03-30"));
        booking1.setBookingType(bT);
        booking1.setUser(tUser);



        //Movie ------Parameters---------------------->
        float mLength = (float) 120.23;
        String barCode = "121212121p";
        String mTitle = "Boss The Movie";
        String mAuthor = "habibi bibi";
        Date mDateRelease = Date.valueOf("2016-03-31");
        float mPrice = (float) 212.99;

        Movie testMovie = new Movie();
        testMovie.setLength(mLength);
        testMovie.setBarcode(barCode);
        testMovie.setTitle(mTitle);
        testMovie.setAuthor(mAuthor);
        testMovie.setDateOfRelease(mDateRelease);
        testMovie.setPrice(mPrice);
        testMovie.setBooking(booking1);
        //------------------------------------------->



        //SAVE------------------->
        lendingRepository.save(bT);

        memberRepository.save(tUser);

        bookingRepository.save(booking1);

        movieRepository.save(testMovie);
        //------------------------------>


        //-----Retrieving from DB/Testing------------------------->
        Movie returnB = movieRepository.findMovieByTitle(mTitle);

        assertNotNull(returnB);
        assertEquals(mLength, returnB.getLength());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(mTitle, returnB.getTitle());
        assertEquals(mAuthor, returnB.getAuthor());
        assertEquals(mDateRelease, returnB.getDateOfRelease());
        assertEquals(mPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        returnB = movieRepository.findMovieByBarcode(barCode);

        assertNotNull(returnB);
        assertEquals(mLength, returnB.getLength());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(mTitle, returnB.getTitle());
        assertEquals(mAuthor, returnB.getAuthor());
        assertEquals(mDateRelease, returnB.getDateOfRelease());
        assertEquals(mPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        returnB = movieRepository.findMovieById(testMovie.getId());

        assertNotNull(returnB);
        assertEquals(mLength, returnB.getLength());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(mTitle, returnB.getTitle());
        assertEquals(mAuthor, returnB.getAuthor());
        assertEquals(mDateRelease, returnB.getDateOfRelease());
        assertEquals(mPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        assertEquals(true, movieRepository.existsMovieById(testMovie.getId()));


    }

}

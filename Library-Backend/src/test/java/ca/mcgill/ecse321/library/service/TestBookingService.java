package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class TestBookingService {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MusicAlbumRepository musicAlbumRepository;


    @InjectMocks
    private BookingService bookingService;

    //Booking Fields
    private static final long BOOKING_ID = 1234;

    //BookingType Fields
    private static final Date RESERVATION_DATE = Date.valueOf("2021-05-29");

    //Book Fields
    private static final String BOOK_TITLE = "bookTitle";

    //Movie Fields
    private static final String MOVIE_TITLE = "movieTitle";

    //MusicAlbum Fields
    private static final String MUSICALBUM_TITLE = "musicAlbumTitle";


    //Member fields
    private static final String USERNAME = "username";
    private static final String PASSWORD = "Password1234";
    private static final String ADDRESS = "1234 University, Montreal, Quebec";
    private static final Member.MemberType MEMBER_TYPE = Member.MemberType.Local;
    private static final Member.MemberStatus MEMBER_STATUS = Member.MemberStatus.Active;
    private static final int MONTHLY_FEE = 0;
    private static final Date START_DATE = Date.valueOf("2020-05-29");


    @BeforeEach
    public void setMockOutput() {
        lenient().when(bookingRepository.findBookingById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOKING_ID)) {

                Booking booking = new Booking();
                booking.setId(BOOKING_ID);



                return booking;
            }

            return null;
        });

        lenient().when(bookRepository.findBookByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK_TITLE)) {

                Book book = new Book();
                book.setTitle(BOOK_TITLE);



                return book;
            }

            return null;
        });

        lenient().when(movieRepository.findMovieByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MOVIE_TITLE)) {

                Movie movie = new Movie();
                movie.setTitle(MOVIE_TITLE);



                return movie;
            }

            return null;
        });

        lenient().when(musicAlbumRepository.findMusicAlbumByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MUSICALBUM_TITLE)) {

                MusicAlbum musicAlbum = new MusicAlbum();
                musicAlbum.setTitle(MUSICALBUM_TITLE);



                return musicAlbum;
            }

            return null;
        });

        lenient().when(memberRepository.findMemberByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                Member member = new Member();

                member.setUsername(USERNAME);
                member.setPassword(PASSWORD);
                member.setAddress(ADDRESS);
                member.setMemberType(MEMBER_TYPE);
                member.setMemberStatus(MEMBER_STATUS);
                member.setMonthlyFee(MONTHLY_FEE);
                member.setStartDate(START_DATE);

                return member;
            }

            return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(bookingRepository.save(any(Booking.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(bookRepository.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(movieRepository.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(musicAlbumRepository.save(any(MusicAlbum.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(reservationRepository.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateBooking(){

    }

    @Test
    public void testGetExistingBooking(){

    }

    @Test
    public void testGetNonExistingBooking(){

    }

    @Test
    public void testCheckOutBook(){

    }

    @Test
    public void testReturnBook(){

    }

    @Test
    public void testDeleteBooking(){

    }

    @Test
    public void testCreateBookingEmpty(){

    }

    @Test
    public void testCreateBookingNoUser(){

    }
}

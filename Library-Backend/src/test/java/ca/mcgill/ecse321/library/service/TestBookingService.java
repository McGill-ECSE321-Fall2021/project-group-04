package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.BookingRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.MobileItem;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import ca.mcgill.ecse321.library.model.Reservation;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class TestBookingService {

    //Booking Fields
    private static final long BOOKING_ID = 1234;
    //BookingType Fields
    private static final Date RESERVATION_DATE = Date.valueOf("2021-05-29");
    //Book with booking fields
    private static final String BOOK_TITLE = "bookTitle";
    //Book without booking fields
    private static final String BOOK_TITLE2 = "bookTitle2";
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

    @BeforeEach
    public void setMockOutput() {
        lenient().when(bookingRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

            List<Booking> list = new LinkedList<Booking>();

            Booking booking = new Booking();
            booking.setId(BOOKING_ID);

            Member member = new Member();
            member.setUsername(USERNAME);
            booking.setUser(member);

            list.add(booking);


            return list;
        });
        lenient().when(bookingRepository.findBookingById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOKING_ID)) {

                Booking booking = new Booking();
                booking.setId(BOOKING_ID);

                Member member = new Member();
                member.setUsername(USERNAME);
                booking.setUser(member);


                return booking;
            }

            return null;
        });

        lenient().when(bookingRepository.existsBookingById(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0).equals(BOOKING_ID);
        });

        lenient().when(bookRepository.findBookByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOK_TITLE)) {

                Book book = new Book();
                book.setTitle(BOOK_TITLE);

                Booking booking = new Booking();
                booking.setId(BOOKING_ID);
                book.setBooking(booking);


                return book;
            }

            if (invocation.getArgument(0).equals(BOOK_TITLE2)) {

                Book book = new Book();
                book.setTitle(BOOK_TITLE2);

                book.setBooking(null);


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

        lenient().when(memberRepository.existsMemberByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0).equals(USERNAME);
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
    public void testCreateBooking() {

        String elementType = "Book";
        String elementId = BOOK_TITLE;
        String user = USERNAME;

        Booking booking = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(booking);
        assertEquals(USERNAME, booking.getUser().getUsername());
        assertEquals(Reservation.class, booking.getBookingType().getClass());

    }

    @Test
    public void testCreateBookingforMovie() {

        String elementType = "Movie";
        String elementId = MOVIE_TITLE;
        String user = USERNAME;

        Booking booking = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(booking);
        assertEquals(USERNAME, booking.getUser().getUsername());
        assertEquals(Reservation.class, booking.getBookingType().getClass());

    }

    @Test
    public void testCreateBookingforMusicAlbum() {

        String elementType = "MusicAlbum";
        String elementId = MUSICALBUM_TITLE;
        String user = USERNAME;

        Booking booking = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(booking);
        assertEquals(USERNAME, booking.getUser().getUsername());
        assertEquals(Reservation.class, booking.getBookingType().getClass());

    }

    @Test
    public void testGetAllBookings() {
        List<Booking> list = null;
        try {
            list = bookingService.getAllBookings();
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(list);
        assertNotNull(list.get(0));
        assertEquals(BOOKING_ID, list.get(0).getId());
        assertEquals(USERNAME, list.get(0).getUser().getUsername());
        assertEquals(1, list.size());
    }


    @Test
    public void testCheckOutBook() {

        Booking booking = null;

        try {
            booking = bookingService.confirmBooking(USERNAME, String.valueOf(BOOKING_ID));
        } catch (IllegalArgumentException e) {

            fail();
        }
        assertNotNull(booking);
        assertEquals(USERNAME, booking.getUser().getUsername());
        assertEquals(BOOKING_ID, booking.getId());
        assertEquals(Lending.class, booking.getBookingType().getClass());

    }

    @Test
    public void testCheckOutBookUserWithoutBooking() {

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.confirmBooking("NoBookingUsername", String.valueOf(BOOKING_ID));
        } catch (IllegalArgumentException e) {

            error = e.getMessage();

        }
        assertNull(booking);
        assertEquals("The customer who has made this booking shall be the one confirming it", error);


    }

    @Test
    public void testCheckOutBookInvalidBooking() {

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.confirmBooking(USERNAME, String.valueOf(34343434));
        } catch (IllegalArgumentException e) {

            error = e.getMessage();

        }
        assertNull(booking);
        assertEquals("There is no booking with the entered id", error);


    }

    @Test
    public void testReturnLibraryItem() {
        MobileItem item = null;

        try {
            item = bookingService.returnLibraryItem("Book", BOOK_TITLE);
        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(item);
        assertEquals(BOOK_TITLE, item.getTitle());
        assertNull(item.getBooking());

    }

    @Test
    public void testReturnNonExistentBook() {
        MobileItem item = null;
        String error = null;
        try {
            item = bookingService.returnLibraryItem("Book", "NonExistentTitle");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("could not find a book with that name ", error);
    }

    @Test
    public void testReturnAlreadyReturnedBook() {
        MobileItem item = null;
        String error = null;
        try {
            item = bookingService.returnLibraryItem("Book", BOOK_TITLE2);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(item);
        assertEquals("Book already returned", error);
    }

    @Test
    public void testReturnNonExistentMovie() {
        MobileItem item = null;
        String error = null;
        try {
            item = bookingService.returnLibraryItem("Movie", "NonExistentTitle");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("could not find a movie with that name ", error);
    }

    @Test
    public void testReturnNonExistentMusicAlbum() {
        MobileItem item = null;
        String error = null;
        try {
            item = bookingService.returnLibraryItem("MusicAlbum", "NonExistentTitle");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("could not find a music album with that name ", error);
    }

    @Test
    public void testReturnNonExistentType() {
        MobileItem item = null;
        String error = null;
        try {
            item = bookingService.returnLibraryItem("wrongtype", "NonExistentTitle");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(item);
        assertEquals("invalid element type ", error);
    }

    @Test
    public void testCreateBookingNonExistentUser() {


        String elementType = "Book";
        String elementId = BOOK_TITLE;
        String user = "NonExistentUser";

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("could not find a customer with that username ", error);
    }

    @Test
    public void testCreateBookingEmptyUserName() {


        String elementType = "Book";
        String elementId = BOOK_TITLE;
        String user = "";

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("Username cannot be empty ", error);
    }

    @Test
    public void testCreateBookingEmptyElementType() {


        String elementType = "";
        String elementId = BOOK_TITLE;
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("elementType cannot be empty ", error);
    }

    @Test
    public void testCreateBookingEmptyElementID() {


        String elementType = "Book";
        String elementId = "";
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("elementId cannot be empty ", error);
    }

    @Test
    public void testCreateBookingNullUserName() {
        String elementType = "Book";
        String elementId = BOOK_TITLE;
        String user = null;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("Username cannot be empty ", error);
    }

    @Test
    public void testCreateBookingNullElementType() {
        String elementType = null;
        String elementId = BOOK_TITLE;
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("elementType cannot be empty ", error);
    }

    @Test
    public void testCreateBookingNullElementID() {

        String elementType = "Book";
        String elementId = null;
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("elementId cannot be empty ", error);
    }

    @Test
    public void testCreateBookingNonExistentBook() {

        String elementType = "Book";
        String elementId = "NonExistentBookTitle";
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("could not find a book with that name ", error);
    }

    @Test
    public void testCreateBookingNonExistentMovie() {

        String elementType = "Movie";
        String elementId = "NonExistentMovieTitle";
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("could not find a movie with that name ", error);
    }

    @Test
    public void testCreateBookingNonExistentMusicAlbum() {

        String elementType = "MusicAlbum";
        String elementId = "NonExistentTitle";
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("could not find a music album with that name ", error);
    }

    @Test
    public void testCreateBookingNonExistentType() {

        String elementType = "nontype";
        String elementId = "NonExistentBookTitle";
        String user = USERNAME;

        Booking booking = null;
        String error = null;

        try {
            booking = bookingService.createBooking(user, elementType, elementId);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(booking);
        assertEquals("invalid element type ", error);
    }
}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Movie;
import java.sql.Date;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

/**
 * @author alymo
 */

@ExtendWith(MockitoExtension.class)
public class TestMovieService {

    private static final String LIBRARIAN_USERNAME = "TestCustomer";
    private static final String AUTHOR = "LOLLS";
    private static final float LENGTH = 233f;
    private static final String DATE_OF_RELEASE = "2021-11-01";
    private static final int PRICE = 30;
    private static final String TITLE = " The meaning of life";
    private static final String BARCODE = "122344";
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(movieRepository.findMovieByBarcode(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BARCODE)) {
                Movie movie = new Movie();
                movie.setAuthor(AUTHOR);
                movie.setLength(LENGTH);
                movie.setDateOfRelease(Date.valueOf(DATE_OF_RELEASE));
                movie.setPrice(PRICE);
                movie.setTitle(TITLE);

                return movie;
            } else return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(movieRepository.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateMovie() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "Aly's movie";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";
        String numberOfPages = "33";

        Movie movie = null;
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            String error = e.getMessage();
            fail();
        }

        assertNotNull(movie);
        assertEquals(Float.valueOf(length), movie.getLength());
        assertEquals(barCode, movie.getBarcode());
        assertEquals(title, movie.getTitle());
        assertEquals(author, movie.getAuthor());
        assertEquals(Date.valueOf(dateOfRelease), movie.getDateOfRelease());
        assertEquals(Float.valueOf(price), movie.getPrice());
        assertNull(movie.getBooking());
    }

    @Test
    public void testCreateMovieNullBarcode() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "";
        String title = "Aly's movie";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "barcode needs to be specified ");
    }

    @Test
    public void testCreateMovieNullTitle() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "title needs to be specified ");
    }

    @Test
    public void testCreateMovieNullAuthor() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "Aly's movie";
        String author = "";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "160";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "author needs to be specified ");
    }

    @Test
    public void testCreateMovieNullDateOfRelease() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "Aly's movie";
        String author = "Aly";
        String dateOfRelease = "";
        String price = "20";
        String length = "160";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "dateOfRelease needs to be specified date format is not correct ");
    }

    @Test
    public void testCreateMovieNullPrice() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "Aly's movie";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "";
        String length = "160";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "price needs to be specified price is not a number ");
    }


    @Test
    public void testCreateMovieNullLength() {
        assertEquals(0, movieService.getAllMovies().size());
        String barCode = "11";
        String title = "Aly's movie";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String length = "";

        Movie movie = null;

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(movie);
        assertEquals(error, "length needs to be specified ");
    }

    @Test
    public void testDeleteMovie() {

        assertEquals(0, movieService.getAllMovies().size());
        Movie movie = null;
        String barCode = "11";
        String title = "Aly's musicAlbum";
        String author = "Aly";
        String dateOfRelease = "2020-02-02";
        String price = "20";
        String length = "160";
        String numberOfSongs = "33";

        String error = "";
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNotNull(movie);

        try {
            movieService.deleteMovie(title);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(movieService.getMovieByTitle(title));
    }


}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Movie;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    /**
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param length
     * @return
     * @author alymo
     * Creates a movie
     */
    @Transactional
    public Movie createMovie(String barCode, String title, String author,
                             String dateOfRelease, String price, String length) {

        MobileItemServices.checkItemInfo(barCode, title, author, dateOfRelease, price);

        String error = "";
        if (length == null || length == "") {
            error += "length needs to be specified ";

        }

        Boolean hasError = error != null || error != "";

        if (hasError) throw new IllegalArgumentException(error);

        Movie movie = new Movie();
        movie.setBarcode(barCode);
        movie.setTitle(title);
        movie.setAuthor(author);
        movie.setDateOfRelease(Date.valueOf(dateOfRelease));
        movie.setPrice(Integer.valueOf(price));
        movie.setLength(Integer.valueOf(length));
        movie.setBooking(null);

        movieRepository.save(movie);

        return movie;

    }

    /**
     * @param title
     * @return
     * @author alymo
     * Gets movie by title
     */
    @Transactional
    public Movie getMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    /**
     * @param title
     * @return
     * @author alymo
     * Deletes movie by title
     */
    @Transactional
    public boolean deleteMovie(String title) {
        Movie movie = movieRepository.findMovieByTitle(title);
        if (movie != null) {
            movieRepository.delete(movie);
            return true;
        } else return false;
    }

    /**
     * @return
     * @author alymo
     * Gets movies from database
     */
    public List<Movie> getAllMovies() {
        return Services.toList(movieRepository.findAll());
    }


}
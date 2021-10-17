package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.Movie;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
    Movie findMovieById(Long id);

    boolean existsMovieById(Long id);

    List<Movie> findByAuthor(String author);

    Movie findMovieByTitle(String title);

    Movie findMovieByBarcode(String barcode);

    List<Movie> findByBooking(Booking booking);
}

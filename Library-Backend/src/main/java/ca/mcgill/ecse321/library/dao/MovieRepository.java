package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
    Movie findMovieByItemId(String id);

    boolean existsMovieByItemId(String id);
}

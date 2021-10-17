package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, String>{
	Movie findMovieByItemId(String id);
	boolean existsMovieByItemId(String id);
}

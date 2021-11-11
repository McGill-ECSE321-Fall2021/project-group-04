package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Movie;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	/**
	 * @author alymo
	 * Creates a movie
	 * @param barCode
	 * @param title
	 * @param author
	 * @param dateOfRelease
	 * @param price
	 * @param length
	 * @return
	 */
	@Transactional
	public Movie createMovie(String barCode, String title, String author,
			String dateOfRelease, String price, String length) {

		MobileItemServices.checkItemInfo(barCode, title, author, dateOfRelease, price);
		
		String error = "";
		if(length == null || length == "") {
			error += "length needs to be specified ";

		}
		
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);

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
	 * @author alymo
	 * Gets movie by title
	 * @param title
	 * @return
	 */
	@Transactional
	public Movie getMovieByTitle(String title) {
		return movieRepository.findMovieByTitle(title);
	}
	
	/**
	 * @author alymo
	 * Deletes movie by title
	 * @param title
	 * @return
	 */
	@Transactional
	public boolean deleteMovie(String title) {
		Movie movie = movieRepository.findMovieByTitle(title);
		if(movie!=null) {
			movieRepository.delete(movie);
			return true;
		}
		else return false;
	}
	
	/**
	 * @author alymo
	 * Gets movies from database
	 * @return
	 */
	public List<Movie> getAllMovies(){
		return Services.toList(movieRepository.findAll());
	}
	
	
}
package ca.mcgill.ecse321.library.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.model.Movie;

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
		
		HelpersBooking.checkMobileItemInfo(barCode, title, author, dateOfRelease, price);
		
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
	
	
	
}
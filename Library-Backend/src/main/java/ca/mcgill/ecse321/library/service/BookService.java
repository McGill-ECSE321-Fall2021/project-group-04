package ca.mcgill.ecse321.library.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.model.Book;





public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	/**
	 * @author alymo
	 * Creates a book
	 * @param barCode
	 * @param title
	 * @param author
	 * @param dateOfRelease
	 * @param price
	 * @param isbn
	 * @param numberOfPages
	 * @return
	 */
	@Transactional
	public Book createBook(String barCode, String title, String author,
			String dateOfRelease, String price, String isbn, String numberOfPages) {
		
		HelpersBooking.checkMobileItemInfo(barCode, title, author, dateOfRelease, price);
		
		String error = "";
		if(isbn == null || isbn == "") {
			error += "isbn needs to be specified ";

		}		
		if(numberOfPages == null || numberOfPages == "") {
			error += "number of pages needs to be specified ";
		}
		
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);

		Book book = new Book();
		book.setBarcode(barCode);
		book.setTitle(title);
		book.setAuthor(author);
		book.setDateOfRelease(Date.valueOf(dateOfRelease));
		book.setPrice(Integer.valueOf(price));
		book.setIsbn(isbn);
		book.setNumberOfPages(Integer.valueOf(numberOfPages));
		book.setBooking(null);
		
		bookRepository.save(book);
		
		return book;
		
	}
	
	
	
}

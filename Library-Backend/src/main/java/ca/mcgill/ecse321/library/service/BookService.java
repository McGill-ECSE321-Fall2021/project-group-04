package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;

import ca.mcgill.ecse321.library.model.Booking;
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
		
		LibraryItemService.checkMobileItemInfo(barCode, title, author, dateOfRelease, price);
		
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
	
	/**
	 * @author Jewoo Lee
	 * Gets book by title
	 * @param title
	 * @return
	 */
	@Transactional
	public Book getBookByTitle(String title) {
		return bookRepository.findBookByTitle(title);
	}
	
	/**
	 * @author Jewoo Lee
	 * Gets book by isbn
	 * @param isbn
	 * @return
	 */
	@Transactional
	public Book getBookByIsbn(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}

	/**
	 * @author Jewoo Lee
	 * @param author
	 * @return
	 */
	@Transactional
	public List getBookByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	/**
	 * @author Jewoo Lee
	 * @param barcode
	 * @return
	 */
	@Transactional
	public Book getBookByBarcode(String barcode) {
		return bookRepository.findBookByBarcode(barcode);
	}

	/**
	 * @author Jewoo Lee
	 * @param id
	 * @return
	 */
	@Transactional
	public Book getBookByID(Long id) {
		return bookRepository.findBookById(id);
	}

	/**
	 * @author Jewoo Lee
	 * @param booking
	 * @return
	 */
	@Transactional
	public List getBookByBooking(Booking booking) {
		return bookRepository.findByBooking(booking);
	}

	
	/**
	 * @author alymo
	 * deletes book by title
	 * @param title
	 * @return
	 */
	@Transactional
	public boolean deleteBook(String title) {
		Book book = bookRepository.findBookByTitle(title);
		if(book != null) {
			bookRepository.delete(book);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * @author Jewoo Lee
	 * returns all books from database
	 * @return
	 */
	public List<Book> getAllBooks(){
		return LibraryItemService.toList(bookRepository.findAll());
	}
	
	
	
}

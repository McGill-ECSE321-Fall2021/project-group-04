package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Booking;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class BookService {

    @Autowired
    BookRepository bookRepository;

    /**
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param isbn
     * @param numberOfPages
     * @return
     * @author Creates a book
     */
    @Transactional
    public Book addBook(String barCode, String title, String author,
                        String dateOfRelease, String price, String isbn, String numberOfPages) {

        MobileItemServices.checkItemInfo(barCode, title, author, dateOfRelease, price);

        String error = "";
        if (isbn == null || isbn == "") {
            error += "isbn needs to be specified ";

        }
        if (numberOfPages == null || numberOfPages == "") {
            error += "number of pages needs to be specified ";
        }

        Boolean hasError = error != null || error != "";

        if (hasError) throw new IllegalArgumentException(error);

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
     * @param title
     * @return
     * @author Gets book by title
     */
    @Transactional
    public Book getBookByTitle(String title) {

        return bookRepository.findBookByTitle(title);
    }

    /**
     * @param isbn
     * @return
     * @author Gets book by isbn
     */
    @Transactional
    public Book getBookByIsbn(String isbn) {

        return bookRepository.findBookByIsbn(isbn);
    }

    @Transactional
    public List getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Transactional
    public Book getBookByBarcode(String barcode) {
        return bookRepository.findBookByBarcode(barcode);
    }

    @Transactional
    public Book getBookByID(Long id) {
        return bookRepository.findBookById(id);
    }

    @Transactional
    public List getBookByBooking(Booking booking) {
        return bookRepository.findByBooking(booking);
    }


    /**
     * @param title
     * @return
     * @author deletes book by title
     */
    @Transactional
    public boolean removeBook(String title) {
        Book book = bookRepository.findBookByTitle(title);
        if (book != null) {
            bookRepository.delete(book);
            return true;
        } else
            return false;
    }

    /**
     * @return
     * @author returns all books from database
     */
    public List<Book> getAllBooks() {

        return Services.toList(bookRepository.findAll());
    }


}

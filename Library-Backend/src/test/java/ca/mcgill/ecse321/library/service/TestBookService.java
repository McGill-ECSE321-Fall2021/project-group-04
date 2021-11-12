package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.model.Book;
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
public class TestBookService {

    private static final String LIBRARIAN_USERNAME = "TestCustomer";
    private static final String ISBN = "1234554653";
    private static final String AUTHOR = "LOLLS";
    private static final int NUMBER_OF_PAGES = 233;
    private static final String DATE_OF_RELEASE = "2021-11-01";
    private static final int PRICE = 30;
    private static final String TITLE = " The meaning of life";
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(bookRepository.findBookByIsbn(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(ISBN)) {
                Book book = new Book();
                book.setAuthor(AUTHOR);
                book.setNumberOfPages(NUMBER_OF_PAGES);
                book.setDateOfRelease(Date.valueOf(DATE_OF_RELEASE));
                book.setPrice(PRICE);
                book.setTitle(TITLE);

                return book;
            } else return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(bookRepository.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateBook() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's Book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());
        assertEquals(barCode, book.getBarcode());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(Date.valueOf(dateOfRelease), book.getDateOfRelease());
        assertEquals(Float.valueOf(price), book.getPrice());
        assertEquals(Integer.valueOf(numberOfPages), book.getNumberOfPages());
        assertNull(book.getBooking());
    }

    @Test
    public void testCreateBookNullBarcode() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "";
        String title = "Aly's Book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "barcode needs to be specified ");
    }

    @Test
    public void testCreateBookNullTitle() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "title needs to be specified ");
    }

    @Test
    public void testCreateBookNullAuthor() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "author needs to be specified ");
    }

    @Test
    public void testCreateBookNullDateOfRelease() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "Aly";
        String dateOfRelease = "";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "dateOfRelease needs to be specified date format is not correct");
    }

    @Test
    public void testCreateBookNullPrice() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "price needs to be specified price is not a number");
    }


    @Test
    public void testCreateBookNullIsbn() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "isbn needs to be specified ");
    }

    @Test
    public void testCreateBookNullNumberOfPages() {
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(book);
        assertEquals(error, "number of pages needs to be specified ");
    }

    @Test
    public void testDeleteBook() {

        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "11";
        String numberOfPages = "33";

        Book book = null;

        String error = "";
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNotNull(book);

        try {
            bookService.deleteBook(title);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();

        }
        assertNull(bookRepository.findBookByTitle(title));
    }

}

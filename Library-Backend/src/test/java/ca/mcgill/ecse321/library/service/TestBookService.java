package ca.mcgill.ecse321.library.service;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import ca.mcgill.ecse321.library.dao.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;

public class TestBookService {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private static final String LIBRARIAN_USERNAME ="TestCustomer";

    private static final String ISBN = "1234554653";
    private static final String AUTHOR = "LOLLS";
    private static final int NUMBER_OF_PAGES = 233;
    private static final String DATE_OF_RELEASE = "2021-11-01";
    private static final int PRICE = 30;
    private static final String TITLE = " The meaning of life";


    @BeforeEach
    public void setMockOutput(){

        lenient().when(bookRepository.findBookByIsbn(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(ISBN)) {
                Book book = new Book();
                book.setAuthor(AUTHOR);
                book.setNumberOfPages(NUMBER_OF_PAGES);
                book.setDateOfRelease(Date.valueOf(DATE_OF_RELEASE));
                book.setPrice(PRICE);
                book.setTitle(TITLE);

                return book;
            }
            else return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(bookRepository.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateBook(){
        assertEquals(0, bookService.getAllBooks().size());
        String barCode = "11";
        String title = "Aly's Book";
        String author = "Aly";
        String dateOfRelease = "2021-01-01";
        String price = "20";
        String isbn = "1";
        String numberOfPages = "33";

        Book book = null;
        try{
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
        }
        catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(book);
        assertEquals(isbn, book.getIsbn());
        assertEquals(barCode, book.getBarcode());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(Date.valueOf(dateOfRelease), book.getDateOfRelease());
        assertEquals(Float.valueOf(price), book.getPrice());
        assertEquals(isbn, book.getIsbn());
        assertEquals(Integer.valueOf(numberOfPages), book.getNumberOfPages());
        assertNull(book.getBooking());
    }
}

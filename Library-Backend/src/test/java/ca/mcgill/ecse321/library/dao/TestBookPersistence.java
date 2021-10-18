package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.*;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LendingRepository lendingRepository;

    @AfterEach
    public void clearDatabase() {
        bookRepository.deleteAll();
        bookingRepository.deleteAll();
        lendingRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadBook() {


        Member tUser = new Member();
        tUser.setUsername("Simo4");
        tUser.setPassword("12341234");
        tUser.setAddress("123 street");
        //tUser.setId((long)1234);


        Lending bT = new Lending();

        Booking booking1= new Booking();
        booking1.setBookingDate(Date.valueOf("2015-03-30"));
        //booking1.setId((long) 33233);
        booking1.setBookingType(bT);
        booking1.setUser(tUser);



        //Book ---------------------------->
        String bIsbn = "Bsbsbssb12";
        int bNumPages = 222;
        String barCode = "121212121p";
        String bTitle = "Boss";
        String bAuthor = "habibi";
        Date bDateRelease = Date.valueOf("2015-03-31");
        float bPrice = (float) 22.99;
        //long itemId = 1234;

        Book testBook = new Book();
        testBook.setIsbn(bIsbn);
        testBook.setNumberOfPages(bNumPages);
        testBook.setBarcode(barCode);
        testBook.setTitle(bTitle);
        testBook.setAuthor(bAuthor);
        testBook.setDateOfRelease(bDateRelease);
        testBook.setPrice(bPrice);
        //testBook.setId(itemId);
        testBook.setBooking(booking1);
        //---------------------------->



        //SAVE------------------->
        lendingRepository.save(bT);

        memberRepository.save(tUser);

        bookingRepository.save(booking1);

        bookRepository.save(testBook);


        Book returnB = bookRepository.findBookByTitle(bTitle);

        assertNotNull(returnB);
        assertEquals(bIsbn, returnB.getIsbn());
        assertEquals(bNumPages, returnB.getNumberOfPages());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(bTitle, returnB.getTitle());
        assertEquals(bAuthor, returnB.getAuthor());
        assertEquals(bDateRelease, returnB.getDateOfRelease());
        assertEquals(bPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        returnB = bookRepository.findBookByBarcode(barCode);

        assertNotNull(returnB);
        assertEquals(bIsbn, returnB.getIsbn());
        assertEquals(bNumPages, returnB.getNumberOfPages());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(bTitle, returnB.getTitle());
        assertEquals(bAuthor, returnB.getAuthor());
        assertEquals(bDateRelease, returnB.getDateOfRelease());
        assertEquals(bPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        returnB = bookRepository.findBookById(testBook.getId());

        assertNotNull(returnB);
        assertEquals(bIsbn, returnB.getIsbn());
        assertEquals(bNumPages, returnB.getNumberOfPages());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(bTitle, returnB.getTitle());
        assertEquals(bAuthor, returnB.getAuthor());
        assertEquals(bDateRelease, returnB.getDateOfRelease());
        assertEquals(bPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());

        returnB = bookRepository.findBookByIsbn(bIsbn);

        assertNotNull(returnB);
        assertEquals(bIsbn, returnB.getIsbn());
        assertEquals(bNumPages, returnB.getNumberOfPages());
        assertEquals(barCode, returnB.getBarcode());
        assertEquals(bTitle, returnB.getTitle());
        assertEquals(bAuthor, returnB.getAuthor());
        assertEquals(bDateRelease, returnB.getDateOfRelease());
        assertEquals(bPrice, returnB.getPrice());
        assertEquals(booking1.getBookingDate(), returnB.getBooking().getBookingDate());



        assertEquals(true,bookRepository.existsBookById(testBook.getId()));

        
        

    }


}

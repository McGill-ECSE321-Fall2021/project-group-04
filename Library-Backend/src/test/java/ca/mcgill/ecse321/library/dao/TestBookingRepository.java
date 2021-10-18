package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.*;
import org.aspectj.apache.bcel.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookingRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LibrarianRepository librarianRepository;

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LendingRepository lendingRepository;

    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
        memberRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadBookingWithMember() {

        Member tUser = (Member) generateUser("Member");


        Lending bT = new Lending();
        Booking booking1 = generateBooking(tUser);

        //Book ---------------------------->
        String bIsbn = "Bsbsbssb12";
        String bIsbn2 = "Bsbsbssb123";
        Book book = generateBook(bIsbn);
        book.setBooking(booking1);

        //SAVE------------------->

        memberRepository.save(tUser);
        bookingRepository.save(booking1);


        //TEST------------------->

        List<Booking> testBooking1 = bookingRepository.findByUser(tUser);
        List<Booking> testBooking2 = bookingRepository.findByUserAndBookingDate(tUser,java.sql.Date.valueOf("2015-03-30"));
        Booking findBooking = bookingRepository.findBookingById(booking1.getId());

        assertNotNull(findBooking);
        assertNotNull(testBooking1);
        assertNotNull(testBooking2);

        compareToDB(booking1);

        assertEquals(booking1.getId(), testBooking2.get(0).getId());



    }

    @Test
    public void testPersistAndLoadBookingWithLibrarian() {


        Librarian tUser = (Librarian) generateUser("Librarian");

        Booking booking1 = generateBooking(tUser);

        //Book ---------------------------->
        String bIsbn = "Bsbsbssb12";
        String bIsbn2 = "Bsbsbssb123";
        Book book = generateBook(bIsbn);
        book.setBooking(booking1);

        //SAVE------------------->

        librarianRepository.save(tUser);
        bookingRepository.save(booking1);


        //TEST------------------->

        List<Booking> testBooking1 = bookingRepository.findByUser(tUser);
        List<Booking> testBooking2 = bookingRepository.findByUserAndBookingDate(tUser,java.sql.Date.valueOf("2015-03-30"));
        Booking findBooking = bookingRepository.findBookingById(booking1.getId());

        assertNotNull(findBooking);
        assertNotNull(testBooking1);
        assertNotNull(testBooking2);

        compareToDB(booking1);

        assertEquals(booking1.getId(), testBooking2.get(0).getId());
    }

    @Test
    public void testPersistAndLoadBookingWithHeadLibrarian() {

        HeadLibrarian tUser = (HeadLibrarian) generateUser("HeadLibrarian");

        Booking booking1 = generateBooking(tUser);

        //Book ---------------------------->
        String bIsbn = "Bsbsbssb12";
        String bIsbn2 = "Bsbsbssb123";
        Book book = generateBook(bIsbn);
        book.setBooking(booking1);

        //SAVE------------------->

        librarianRepository.save(tUser);
        bookingRepository.save(booking1);


        //TEST------------------->

        List<Booking> testBooking1 = bookingRepository.findByUser(tUser);
        List<Booking> testBooking2 = bookingRepository.findByUserAndBookingDate(tUser,java.sql.Date.valueOf("2015-03-30"));
        Booking findBooking = bookingRepository.findBookingById(booking1.getId());

        assertNotNull(findBooking);
        assertNotNull(testBooking1);
        assertNotNull(testBooking2);

        compareToDB(booking1);

        assertEquals(booking1.getId(), testBooking2.get(0).getId());
    }


    private User generateUser(String type){

        User tUser;

        if(type.equals("Member")){
            tUser = new Member();
        }
        else if (type.equals("Librarian")){
            tUser = new Librarian();
        }
        else {
            tUser = new HeadLibrarian();
        }

        tUser.setUsername("Simo4");
        tUser.setPassword("12341234");
        tUser.setAddress("123 street");
        tUser.setAddress("1234 Aly street");
        return tUser;
    }

    private Booking generateBooking(User tUser){
        Lending bT = new Lending();
        Booking booking1= new Booking();
        booking1.setBookingDate(java.sql.Date.valueOf("2015-03-30"));
        booking1.setBookingType(bT);
        booking1.setUser(tUser);
        lendingRepository.save(bT);

        return booking1;
    }

    private Book generateBook(String bIsbn){
        //Book ---------------------------->
        // String bIsbn = "Bsbsbssb12";
        int bNumPages = 222;
        String barCode = "121212121p";
        String bTitle = "Boss";
        String bAuthor = "habibi";
        java.sql.Date bDateRelease = Date.valueOf("2015-03-31");
        float bPrice = (float) 22.99;

        Book testBook = new Book();
        testBook.setIsbn(bIsbn);
        testBook.setNumberOfPages(bNumPages);
        testBook.setBarcode(barCode);
        testBook.setTitle(bTitle);
        testBook.setAuthor(bAuthor);
        testBook.setDateOfRelease(bDateRelease);
        testBook.setPrice(bPrice);

        return testBook;
    }

    private void compareToDB (Booking expected){
        //TEST------------------->


        Booking findBooking = bookingRepository.findBookingById(expected.getId());

        assertNotNull(findBooking);

        assertEquals(expected.getId(), findBooking.getId());
        assertEquals(expected.getBookingDate(), findBooking.getBookingDate());
        assertEquals(expected.getBookingType().toString().split("@")[0], findBooking.getBookingType().toString().split("@")[0]);
        assertEquals(expected.getUser().getUsername(), findBooking.getUser().getUsername());

//        assertEquals(booking1.getId(), testBooking2.get(0).getId());
//        assertEquals(booking1.getId(), testBooking1.get(0).getId());
//        assertEquals(booking2.getId(), testBooking1.get(1).getId());
    }

}

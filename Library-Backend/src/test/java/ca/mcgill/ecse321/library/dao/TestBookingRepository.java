package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @AfterEach
    public void clearDatabase() {
        bookingRepository.deleteAll();
        memberRepository.deleteAll();
        librarianRepository.deleteAll();
        headLibrarianRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadBookingWithMember() {
    }

    @Test
    public void testPersistAndLoadBookingWithLibrarian() {
    }

    @Test
    public void testPersistAndLoadBookingWithHeadLibrarian() {
    }
}

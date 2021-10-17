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
public class TestMoviePersistence {
    @Autowired
    EntityManager entityManager;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    BookingRepository bookingRepository;

    @AfterEach
    public void clearDatabase() {
        movieRepository.deleteAll();
        bookingRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadMovie() {
    }

}

package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Lending;
import java.sql.Date;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLendingPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    LendingRepository lendingRepository;

    @AfterEach
    public void clearDatabase() {
        lendingRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadLending() {

        Lending lending = new Lending();
        Date expDate = Date.valueOf("2015-03-31");
        lending.setReturnDate(expDate);

        lendingRepository.save(lending);

        assertNotNull(lendingRepository.findLendingById(lending.getId()));
        assertTrue(lendingRepository.existsLendingById(lending.getId()));
        assertEquals(lending.getId(), lendingRepository.findByReturnDate(expDate).get(0).getId());
    }
}

package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Newspaper;
import java.sql.Date;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestNewspaperPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    NewspaperRepository newspaperRepository;

    @AfterEach
    public void clearDatabase() {
        newspaperRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadNewspaperById() {
        Newspaper newspaper = new Newspaper();

        String title = "New York Times";
        Date date = Date.valueOf("2015-08-20");
        int numberOfPages = 15;

        newspaper.setTitle(title);
        newspaper.setDate(date);
        newspaper.setNumberOfPages(numberOfPages);

        newspaperRepository.save(newspaper);
        Long id = newspaper.getId();

        newspaper = null;
        newspaper = newspaperRepository.findNewspaperById(id);

        assertTrue(newspaperRepository.existsNewspaperById(id));
        assertNotNull(newspaper);
        assertEquals(id, newspaper.getId());
        assertEquals(title, newspaper.getTitle());
        assertEquals(date, newspaper.getDate());
        assertEquals(numberOfPages, newspaper.getNumberOfPages());
    }

    @Test
    public void testPersistAndLoadNewspaperByDate() {
        Newspaper newspaper = new Newspaper();

        String title = "New York Times";
        Date date = Date.valueOf("2015-08-20");
        int numberOfPages = 15;

        newspaper.setTitle(title);
        newspaper.setDate(date);
        newspaper.setNumberOfPages(numberOfPages);

        newspaperRepository.save(newspaper);
        Long id = newspaper.getId();

        newspaper = null;
        newspaper = newspaperRepository.findNewspaperByDate(date);

        assertTrue(newspaperRepository.existsNewspaperById(id));
        assertNotNull(newspaper);
        assertEquals(id, newspaper.getId());
        assertEquals(title, newspaper.getTitle());
        assertEquals(date, newspaper.getDate());
        assertEquals(numberOfPages, newspaper.getNumberOfPages());
    }
}

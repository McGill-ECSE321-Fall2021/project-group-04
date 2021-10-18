package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Archive;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
public class TestArchivePersistence {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private ArchiveRepository archiveRepository;

    @AfterEach
    public void clearDatabase() {
        archiveRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadArchiveWithId() {
        Archive archive = new Archive();

        String title = "Old Archive";
        Date date = Date.valueOf("1997-08-20");
        int numberOfPages = 5;

        archive.setTitle(title);
        archive.setDate(date);
        archive.setNumberOfPages(numberOfPages);

        archiveRepository.save(archive);
        Long id = archive.getId();

        archive = null;

        archive = archiveRepository.findArchiveById(id);

        assertTrue(archiveRepository.existsArchiveById(id));
        assertNotNull(archive);
        assertEquals(id, archive.getId());
        assertEquals(title, archive.getTitle());
        assertEquals(date, archive.getDate());
        assertEquals(numberOfPages, archive.getNumberOfPages());
    }

    @Test
    public void testPersistAndLoadArchiveWithTitle() {
        Archive archive = new Archive();

        String title = "Old Archive";
        Date date = Date.valueOf("1997-08-20");
        int numberOfPages = 5;

        archive.setTitle(title);
        archive.setDate(date);
        archive.setNumberOfPages(numberOfPages);

        archiveRepository.save(archive);
        Long id = archive.getId();

        archive = null;

        archive = archiveRepository.findArchiveByTitle(title);

        assertTrue(archiveRepository.existsArchiveById(id));

        assertNotNull(archive);
        assertEquals(id, archive.getId());
        assertEquals(title, archive.getTitle());
        assertEquals(date, archive.getDate());
        assertEquals(numberOfPages, archive.getNumberOfPages());
    }

    @Test
    public void testPersistAndLoadArchiveWithDate() {
        int n = 3;
        List<Archive> archives = new ArrayList<>(n);

        String[] titles = {"Old Archive", "Same date", "Ancient Egypt"};
        Date[] dates = {Date.valueOf("1997-08-20"), Date.valueOf("1997-08-20"), Date.valueOf("1178-09-15")};
        int[] numberOfPages = {5, 15, 234};

        for (int i = 0; i < n; i++) {
            Archive archive = new Archive();
            archive.setTitle(titles[i]);
            archive.setDate(dates[i]);
            archive.setNumberOfPages(numberOfPages[i]);
            archives.add(archive);
        }


        archiveRepository.saveAll(archives);
        Long[] ids = archives.stream().map(Archive::getId).toArray(Long[]::new);

        archives = null;

        archives = archiveRepository.findByDate(dates[0]);

        assertNotNull(archives);

        for (Long id : ids) {
            assertTrue(archiveRepository.existsArchiveById(id));
        }

        for (int i = 0; i < n - 1; i++) {
            Archive archive = archives.get(i);

            assertEquals(ids[i], archive.getId());
            assertEquals(titles[i], archive.getTitle());
            assertEquals(dates[i], archive.getDate());
            assertEquals(numberOfPages[i], archive.getNumberOfPages());
        }
    }
}

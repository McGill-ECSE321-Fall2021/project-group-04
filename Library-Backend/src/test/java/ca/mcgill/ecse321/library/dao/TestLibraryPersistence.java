package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.Library;
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
public class TestLibraryPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    WorkDayRepository workDayRepository;

    @AfterEach
    public void clearDatabase() {
        workDayRepository.deleteAll();
        libraryRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadLibrary() {
        String name = "Group 4 Library";
        String address = "Address";
        Long libraryID = 123456789L;

        Library library = new Library();
        library.setName(name);
        library.setAddress(address);
        library.setId(libraryID);

        libraryRepository.save(library);
        library = null;
        library = libraryRepository.findLibraryById(libraryID);
        library = libraryRepository.findLibraryByNameAndAddress(name, address);

        assertNotNull(library);
        assertEquals(name, library.getName());
        assertEquals(address, library.getAddress());
        assertEquals(libraryID, library.getId());

    }
}

package ca.mcgill.ecse321.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.library.dao.LibraryRepository;
import ca.mcgill.ecse321.library.model.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private LibraryRepository libraryRepository;

    @AfterEach
    public void clearDatabase() {
        libraryRepository.deleteAll();
    }

    @Test
    void contextLoads() {
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

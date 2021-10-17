package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.Librarian;
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
public class TestLibrarianPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    LibrarianRepository librarianRepository;

    @Autowired
    WorkDayRepository workDayRepository;

    @AfterEach
    public void clearDatabase() {
        librarianRepository.deleteAll();
        workDayRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadLibrarian() {
        String username="Librarian";
        String pass="pass";
        String add="1414 Chomedey";
        //Long myid = 9645643L;
        Librarian testL=new Librarian();
        testL.setAddress(add);
        //testL.setId(myid);
        testL.setPassword(pass);
        testL.setUsername(username);


        librarianRepository.save(testL);

        testL=null;

        testL=librarianRepository.findLibrarianByUsername(username);
        assertNotNull(testL);
        assertEquals(username, testL.getUsername());
        assertEquals(pass, testL.getPassword());
        assertEquals(add, testL.getAddress());
        //assertEquals(myid, testL.getId());
    }
}

package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestHeadLibrarianPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;

    @Autowired
    WorkDayRepository workDayRepository;

    @AfterEach
    public void clearDatabase() {
        headLibrarianRepository.deleteAll();
        workDayRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadHeadLibrarian() {
        String username="headLibrarian";
        String pass="pass";
        String add="1414 Chomedey";
        //Long myid = 479857L;
        HeadLibrarian testHL=new HeadLibrarian();
        testHL.setAddress(add);
        //testHL.setId(myid);
        testHL.setPassword(pass);
        testHL.setUsername(username);

        headLibrarianRepository.save(testHL);

        testHL=null;

        testHL=headLibrarianRepository.findHeadLibrarianByUsername(username);
        assertNotNull(testHL);
        assertEquals(username, testHL.getUsername());
        assertEquals(pass, testHL.getPassword());
        assertEquals(add, testHL.getAddress());
        //assertEquals(myid, testHL.getId());



    }
}

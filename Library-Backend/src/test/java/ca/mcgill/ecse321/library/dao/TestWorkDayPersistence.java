package ca.mcgill.ecse321.library.dao;

import java.sql.Time;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.model.WorkDay.DayOfWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestWorkDayPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    WorkDayRepository workDayRepository;

    @AfterEach
    public void clearDatabase() {
        workDayRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadWorkDay() {	
    	
    }
}

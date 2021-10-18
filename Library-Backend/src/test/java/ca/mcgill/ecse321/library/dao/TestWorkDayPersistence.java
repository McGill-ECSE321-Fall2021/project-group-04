package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    	
    	WorkDay testWorkday = new WorkDay();
    	Time startTime = Time.valueOf("08:00:00");
    	Time endTime = Time.valueOf("18:00:00");
    	//DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime
    	testWorkday.setDayOfWeek(DayOfWeek.Wednesday);
    	testWorkday.setStartTime(startTime);
    	testWorkday.setEndTime(endTime);
    	
    	workDayRepository.save(testWorkday);
    	testWorkday = workDayRepository.findWorkDayById(testWorkday.getId());
    	//Not null
    	assertNotNull(testWorkday);
    	
    	assertEquals(DayOfWeek.Wednesday,testWorkday.getDayOfWeek());
    	assertEquals("08:00:00", testWorkday.getStartTime());
    	assertEquals("18:00:00", testWorkday.getEndTime());
    	//boolean true
    	assertEquals(true,workDayRepository.existsWorkDayById(testWorkday.getId()));
    	
    }
}

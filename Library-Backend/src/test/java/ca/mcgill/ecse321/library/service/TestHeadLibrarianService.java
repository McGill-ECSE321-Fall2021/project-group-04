package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.WorkDayRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.model.WorkDay.DayOfWeek;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestHeadLibrarianService {

    @Mock
    private HeadLibrarianRepository headLibrarianRepository;

    @Mock
    private LibrarianRepository librarianRepository;

    @InjectMocks
    private HeadLibrarianService headLibrarianService;

    @InjectMocks
    private LibrarianService librarianService;

    private static final String HEAD_LIBRARIAN_USERNAME = "simo";
    private static final String HEAD_LIBRARIAN_PASSWORD = "simo123";
    private static final String HEAD_LIBRARIAN_ADDRESS = "1234 University, Montreal, QC";
    private static final String HEAD_LIBRARIAN_MONDAY_START = "09:00:00";
    private static final String HEAD_LIBRARIAN_MONDAY_END = "17:00:00";
    private static final String HEAD_LIBRARIAN_TUESDAY_START = "09:00:00";
    private static final String HEAD_LIBRARIAN_TUESDAY_END = "17:00:00";
    private static final String HEAD_LIBRARIAN_WEDNESDAY_START = "09:00:00";
    private static final String HEAD_LIBRARIAN_WEDNESDAY_END = "17:00:00";
    private static final String HEAD_LIBRARIAN_THURSDAY_START = "09:00:00";
    private static final String HEAD_LIBRARIAN_THURSDAY_END = "17:00:00";
    private static final String HEAD_LIBRARIAN_FRIDAY_START = "09:00:00";
    private static final String HEAD_LIBRARIAN_FRIDAY_END = "17:00:00";

    private static final String LIBRARIAN_USERNAME = "aly";
    private static final String LIBRARIAN_PASSWORD = "Aly123456";
    private static final String LIBRARIAN_ADDRESS = "1234 University, Montreal, QC";

    private static final String LIBRARIAN_MONDAY_START = "09:00:00";
    private static final String LIBRARIAN_MONDAY_END = "17:00:00";

    private static final String LIBRARIAN_TUESDAY_START = "09:00:00";
    private static final String LIBRARIAN_TUESDAY_END = "17:00:00";

    private static final String LIBRARIAN_WEDNESDAY_START = "09:00:00";
    private static final String LIBRARIAN_WEDNESDAY_END = "17:00:00";

    private static final String LIBRARIAN_THURSDAY_START = "09:00:00";
    private static final String LIBRARIAN_THURSDAY_END = "17:00:00";

    private static final String LIBRARIAN_FRIDAY_START = "09:00:00";
    private static final String LIBRARIAN_FRIDAY_END = "17:00:00";

    @BeforeEach
    public void setMockOutput() {

        lenient().when(headLibrarianRepository.findHeadLibrarianByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(HEAD_LIBRARIAN_USERNAME)) {
                List<HeadLibrarian> headlibrarianList = new ArrayList<>();

                HeadLibrarian librarian = new HeadLibrarian();

                librarian.setUsername(HEAD_LIBRARIAN_USERNAME);
                librarian.setPassword(HEAD_LIBRARIAN_PASSWORD);
                librarian.setAddress(HEAD_LIBRARIAN_ADDRESS);

                Set<WorkDay> workDays = new HashSet<>();

                WorkDay monday = new WorkDay();
                monday.setDayOfWeek(WorkDay.DayOfWeek.Monday);
                monday.setStartTime(Time.valueOf(HEAD_LIBRARIAN_MONDAY_START));
                monday.setEndTime(Time.valueOf(HEAD_LIBRARIAN_MONDAY_END));
                workDays.add(monday);

                WorkDay tuesday = new WorkDay();
                tuesday.setDayOfWeek(WorkDay.DayOfWeek.Tuesday);
                tuesday.setStartTime(Time.valueOf(HEAD_LIBRARIAN_TUESDAY_START));
                tuesday.setEndTime(Time.valueOf(HEAD_LIBRARIAN_TUESDAY_END));
                workDays.add(tuesday);

                WorkDay wednesday = new WorkDay();
                wednesday.setDayOfWeek(WorkDay.DayOfWeek.Wednesday);
                wednesday.setStartTime(Time.valueOf(HEAD_LIBRARIAN_WEDNESDAY_START));
                wednesday.setEndTime(Time.valueOf(HEAD_LIBRARIAN_WEDNESDAY_END));
                workDays.add(wednesday);

                WorkDay thursday = new WorkDay();
                thursday.setDayOfWeek(WorkDay.DayOfWeek.Thursday);
                thursday.setStartTime(Time.valueOf(HEAD_LIBRARIAN_THURSDAY_START));
                thursday.setEndTime(Time.valueOf(HEAD_LIBRARIAN_THURSDAY_END));
                workDays.add(thursday);

                WorkDay friday = new WorkDay();
                friday.setDayOfWeek(WorkDay.DayOfWeek.Friday);
                friday.setStartTime(Time.valueOf(HEAD_LIBRARIAN_FRIDAY_START));
                friday.setEndTime(Time.valueOf(HEAD_LIBRARIAN_FRIDAY_END));
                workDays.add(friday);

                librarian.setWorkHours(workDays);

                headlibrarianList.add(librarian);


                return librarian;
            }

            return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(headLibrarianRepository.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateLibrarian() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, password, address);
            assertNotNull(librarian);
            assertEquals(username, librarian.getUsername());
            assertEquals(password, librarian.getPassword());
            assertEquals(address, librarian.getAddress());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testCreateLibrarianNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = null;
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, password, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username cannot be empty.");
        }

        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, password, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username cannot be empty.");
        }

        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianNotUnique() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, password, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username already exists.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String password = null;

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, password, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfAllLowercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsaziz123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one uppercase character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfAllUppercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "ITSAZIZ123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one lowercase character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfTooShort() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "hiI1";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be less than 8 characters.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfTooLong() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsaasfwgwrgwefwefwvwdvwfscwdvwrevrevwrgweAAAziz123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be more than 20 characters.");
        }
        assertNull(librarian);
    }

    @Test
    public void testCreateLibrarianPasswordIfNoNumber() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsazizacascAA";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one numeric character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPassword() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "NewPassword1234";

        Librarian librarian;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);

            assertNotNull(librarian);
            assertEquals(username, librarian.getUsername());
            assertEquals(newPassword, librarian.getPassword());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = null;

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfAllLowercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsaziz123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one uppercase character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfAllUppercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "ITSAZIZ123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one lowercase character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfTooShort() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "hiI1";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be less than 8 characters.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfTooLong() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsaasfwgwrgwefwefwvwdvwfscwdvwrevrevwrgweAAAziz123";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be more than 20 characters.");
        }
        assertNull(librarian);
    }

    @Test
    public void testChangeLibrarianPasswordIfNoNumber() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsazizacascAA";

        Librarian librarian = null;
        try {
            librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one numeric character.");
        }
        assertNull(librarian);
    }

    @Test
    public void testDeleteLibrarian() {
        try {
            boolean deleted = headLibrarianService.deleteHeadLibrarian(HEAD_LIBRARIAN_USERNAME);
            assertTrue(deleted);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteUnknownLibrarian() {
        boolean deleted = false;
        try {
            deleted = headLibrarianService.deleteHeadLibrarian("randomusername");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Head librarian is not found.");
        }
        assertFalse(deleted);
    }

    @Test
    public void testAssignSchedulelibrarianNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String librarian = LIBRARIAN_USERNAME;
        String headlibrarian = HEAD_LIBRARIAN_USERNAME;
        WorkDay.DayOfWeek workday = null;
        Time startTime = null;
        Time endTime = null;
        String error = null;
        Set<WorkDay> workDays = null;


        try {
            workDays = headLibrarianService.AssignScheduleLibrarian(workday, startTime, endTime, librarian, headlibrarian);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(workDays);
        assertEquals(error, "Only a headlibrarian can assign schedules.");

    }

    @Test
    public void testAssignSchedulelibrarianEmpty() {

        String librarian = LIBRARIAN_USERNAME;
        String headlibrarian = HEAD_LIBRARIAN_USERNAME;
        WorkDay.DayOfWeek workday = null;
        Time startTime = Time.valueOf("10:00:00");
        Time endTime = Time.valueOf("12:00:00");
        String error = null;
        Set<WorkDay> workDays = null;

        try {
            workDays = headLibrarianService.AssignScheduleLibrarian(workday, startTime, endTime, librarian, headlibrarian);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(workDays);
        assertEquals(error, "Only a headlibrarian can assign schedules.");

    }
    
    @Test
    public void testAssignScheduleHeadLibrarianNull() {
        String headlibrarian = HEAD_LIBRARIAN_USERNAME;
        WorkDay.DayOfWeek workday = null;
        Time startTime = null;
        Time endTime = null;
        String error = null;
        Set<WorkDay> workDays = null;


        try {
            workDays = headLibrarianService.AssignScheduleHeadLibrarian(workday, startTime, endTime, headlibrarian);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(workDays);
        assertEquals(error, "Only a headlibrarian can assign schedules.");
    }

    @Test
    public void testAssignScheduleHeadLibrarianEmpty() {
    	String headlibrarian = HEAD_LIBRARIAN_USERNAME;
        WorkDay.DayOfWeek workday = null;
        Time startTime = Time.valueOf("10:00:00");
        Time endTime = Time.valueOf("12:00:00");
        String error = null;
        Set<WorkDay> workDays = null;

        try {
            workDays = headLibrarianService.AssignScheduleHeadLibrarian(workday, startTime, endTime, headlibrarian);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(workDays);
        assertEquals(error, "Only a headlibrarian can assign schedules.");
    }

    

    @Test
    public void testDeletHeadLibrarianSchedule() {
    	String headlibrarian = HEAD_LIBRARIAN_USERNAME;
    	String librarian = LIBRARIAN_USERNAME;
    	WorkDay.DayOfWeek workday = DayOfWeek.Friday;
        Time startTime = Time.valueOf("10:00:00");
        Time endTime = Time.valueOf("12:00:00");
        String error = null;
    	
    	try {
            boolean deleted = headLibrarianService.DeleteSchedule(workday, startTime, endTime, librarian, headlibrarian);
            assertTrue(deleted);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        	assertEquals(error, "Only a headlibrarian can assign schedules.");
        }
    }

}


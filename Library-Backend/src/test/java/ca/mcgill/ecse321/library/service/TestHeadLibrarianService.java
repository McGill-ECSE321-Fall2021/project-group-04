package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.WorkDay;
import java.sql.Time;
import java.util.HashSet;
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

    @InjectMocks
    private HeadLibrarianService headLibrarianService;

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

    @BeforeEach
    public void setMockOutput() {
        lenient().when(headLibrarianRepository.findHeadLibrarianByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HEAD_LIBRARIAN_USERNAME)) {
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
        } catch(IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testCreateLibrarianNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = null;
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, password, address);
            assertNotNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username cannot be empty.");
        }
    }

    @Test
    public void testCreateLibrarianEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, password, address);
            assertNotNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username cannot be empty.");
        }


    }

    @Test
    public void testCreateLibrarianNotUnique() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, password, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Username already exists.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String password = null;

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, password, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfAllLowercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsaziz123";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one uppercase character.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfAllUppercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "ITSAZIZ123";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one lowercase character.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfTooShort() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "hiI1";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be less than 8 characters.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfTooLong() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsaasfwgwrgwefwefwvwdvwfscwdvwrevrevwrgweAAAziz123";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be more than 20 characters.");
        }
    }

    @Test
    public void testCreateLibrarianPasswordIfNoNumber() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = "TestUsername";
        String address = "1234 Test, Address, Province";
        String newPassword = "itsazizacascAA";

        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(username, newPassword, address);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one numeric character.");
        }
    }

    @Test
    public void testChangeLibrarianPassword() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "NewPassword1234";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNotNull(librarian);
            assertEquals(username, librarian.getUsername());
            assertEquals(newPassword, librarian.getPassword());
        } catch(IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfNull() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = null;

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfEmpty() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Password cannot be empty.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfAllLowercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsaziz123";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one uppercase character.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfAllUppercase() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "ITSAZIZ123";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one lowercase character.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfTooShort() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "hiI1";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be less than 8 characters.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfTooLong() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsaasfwgwrgwefwefwvwdvwfscwdvwrevrevwrgweAAAziz123";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password length cannot be more than 20 characters.");
        }
    }

    @Test
    public void testChangeLibrarianPasswordIfNoNumber() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());

        String username = HEAD_LIBRARIAN_USERNAME;
        String newPassword = "itsazizacascAA";

        try {
            Librarian librarian = headLibrarianService.changeHeadLibrarianPassword(username, newPassword);
            assertNull(librarian);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The password must contain at least one numeric character.");
        }
    }

    @Test
    public void testDeleteLibrarian() {
        try {
            boolean deleted = headLibrarianService.deleteHeadLibrarian(HEAD_LIBRARIAN_USERNAME);
            assertTrue(deleted);
        }
        catch(IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteUnknownLibrarian() {
        try {
            boolean deleted = headLibrarianService.deleteHeadLibrarian("randomusername");
            assertFalse(deleted);
        }
        catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Head librarian is not found.");
        }
    }
}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestLibrarianService {

    @Mock
    private LibrarianRepository librarianRepository;

    private static final String LIBRARIAN_USERNAME = "aly";
    private static final String LIBRARIAN_PASSWORD = "aly123";
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
        lenient().when(librarianRepository.findLibrarianByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(LIBRARIAN_USERNAME)) {
                Librarian librarian = new Librarian();

                librarian.setUsername(LIBRARIAN_USERNAME);
                librarian.setPassword(LIBRARIAN_PASSWORD);
                librarian.setAddress(LIBRARIAN_ADDRESS);

                Set<WorkDay> workDays = new HashSet<>();

                WorkDay monday = new WorkDay();
                monday.setDayOfWeek(WorkDay.DayOfWeek.Monday);
                monday.setStartTime(Time.valueOf(LIBRARIAN_MONDAY_START));
                monday.setEndTime(Time.valueOf(LIBRARIAN_MONDAY_END));
                workDays.add(monday);

                WorkDay tuesday = new WorkDay();
                tuesday.setDayOfWeek(WorkDay.DayOfWeek.Tuesday);
                tuesday.setStartTime(Time.valueOf(LIBRARIAN_TUESDAY_START));
                tuesday.setEndTime(Time.valueOf(LIBRARIAN_TUESDAY_END));
                workDays.add(tuesday);

                WorkDay wednesday = new WorkDay();
                wednesday.setDayOfWeek(WorkDay.DayOfWeek.Wednesday);
                wednesday.setStartTime(Time.valueOf(LIBRARIAN_WEDNESDAY_START));
                wednesday.setEndTime(Time.valueOf(LIBRARIAN_WEDNESDAY_END));
                workDays.add(wednesday);

                WorkDay thursday = new WorkDay();
                thursday.setDayOfWeek(WorkDay.DayOfWeek.Thursday);
                thursday.setStartTime(Time.valueOf(LIBRARIAN_THURSDAY_START));
                thursday.setEndTime(Time.valueOf(LIBRARIAN_THURSDAY_END));
                workDays.add(thursday);

                WorkDay friday = new WorkDay();
                friday.setDayOfWeek(WorkDay.DayOfWeek.Friday);
                friday.setStartTime(Time.valueOf(LIBRARIAN_FRIDAY_START));
                friday.setEndTime(Time.valueOf(LIBRARIAN_FRIDAY_END));
                workDays.add(friday);

                librarian.setWorkHours(workDays);

                return librarian;
            }

            return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateLibrarian() {}

    @Test
    public void testCreateLibrarianNull() {}

    @Test
    public void testCreateLibrarianEmpty() {}

    @Test
    public void testCreateLibrarianNotUnique() {}

    @Test
    public void testChangeLibrarianPassword() {}

    @Test
    public void testChangeLibrarianPasswordIfNull() {}

    @Test
    public void testChangeLibrarianPasswordIfInvalid() {}

    @Test
    public void testDeleteLibrarian() {}

    @Test
    public void testDeleteUnknownLibrarian() {}

}

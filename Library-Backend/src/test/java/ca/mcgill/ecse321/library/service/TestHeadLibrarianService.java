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
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestHeadLibrarianService {

    @Mock
    private HeadLibrarianRepository headLibrarianRepository;

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
    public void testCreateHeadLibrarian() {}

    @Test
    public void testCreateHeadLibrarianNull() {}

    @Test
    public void testCreateHeadLibrarianEmpty() {}

    @Test
    public void testCreateHeadLibrarianNotUnique() {}

    @Test
    public void testChangeHeadLibrarianPassword() {}

    @Test
    public void testChangeHeadLibrarianPasswordIfNull() {}

    @Test
    public void testChangeHeadLibrarianPasswordIfInvalid() {}

    @Test
    public void testDeleteHeadLibrarian() {}

    @Test
    public void testDeleteUnknownHeadLibrarian() {}
}
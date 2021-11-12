package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.WorkDay;
import java.sql.Date;
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
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    private static final String MEMBER_USERNAME = "aziz";
    private static final String MEMBER_PASSWORD = "aziz123";
    private static final String MEMBER_ADDRESS = "1234 University, Montreal, QC";
    private static final Member.MemberType MEMBER_TYPE = Member.MemberType.Local;
    private static final Member.MemberStatus MEMBER_STATUS = Member.MemberStatus.Active;
    private static final int MEMBER_MONTHLY_FEE = 0;
    private static final Date MEMBER_START_DATE = Date.valueOf("2020-05-29");
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
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private LibrarianRepository librarianRepository;
    @Mock
    private HeadLibrarianRepository headLibrarianRepository;
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private MemberService memberService;
    @InjectMocks
    private LibrarianService librarianService;
    @InjectMocks
    private HeadLibrarianService headLibrarianService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(memberRepository.findMemberByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(MEMBER_USERNAME)) {
                Member member = new Member();

                member.setUsername(MEMBER_USERNAME);
                member.setPassword(MEMBER_PASSWORD);
                member.setAddress(MEMBER_ADDRESS);
                member.setMemberType(MEMBER_TYPE);
                member.setMemberStatus(MEMBER_STATUS);
                member.setMonthlyFee(MEMBER_MONTHLY_FEE);
                member.setStartDate(MEMBER_START_DATE);

                return member;
            }

            return null;
        });

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

        lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(headLibrarianRepository.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testLogInMember() {
        assertEquals(0, memberService.getAllMembers().size());
        User user = null;

        String username = "aziz";
        String password = "aziz123";

        try {
            if (MEMBER_USERNAME == username && MEMBER_PASSWORD == password) {
                user = userService.login(username, password);
            }
        } catch (IllegalArgumentException e) {
            fail();
        }
    }


    @Test
    public void testLogInLibrarian() {
        assertEquals(0, librarianService.getAllLibrarians().size());
        Librarian librarian = null;

        String username = LIBRARIAN_USERNAME;
        String password = LIBRARIAN_PASSWORD;

        try {
            librarian = librarianService.login(username, password);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testLogInHeadLibrarian() {
        assertEquals(0, headLibrarianService.getAllHeadLibrarians().size());
        Librarian headLibrarian = null;

        String username = "aly";
        String password = "aly123";

        try {
            if (HEAD_LIBRARIAN_USERNAME == username && HEAD_LIBRARIAN_PASSWORD == password) {
                headLibrarian = headLibrarianService.login(username, password);
            }
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testLogInInvalidUsername() {
        assertEquals(0, memberService.getAllMembers().size());
        User user = null;
        String error = null;

        String username = "abcd";
        String password = "aziz123";

        try {
            user = userService.login(username, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertEquals(error, "Invalid Username.");
    }

    @Test
    public void testLogInInvalidPassoword() {
        assertEquals(0, memberService.getAllMembers().size());
        User user = null;
        String error = null;

        String username = MEMBER_USERNAME;
        String password = "abcd123";

        try {
            //if(MEMBER_USERNAME == username && MEMBER_PASSWORD == password) {
            user = userService.login(username, password);
            //}
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertEquals(error, "Incorrect Password.");
    }

}

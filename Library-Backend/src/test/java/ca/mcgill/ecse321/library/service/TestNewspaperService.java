package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.NewspaperRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Newspaper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;



@ExtendWith(MockitoExtension.class)
public class TestNewspaperService {

    @Mock
    private NewspaperRepository newspaperRepository;

    @InjectMocks
    private NewspaperService newspaperService;

    private static final Date NEWSPAPER_DATE = Date.valueOf("2021-11-11");
    private static final String NEWSPAPER_TITLE = "Win";
    private static final Integer NEWSPAPER_NUMBEROFPAGES = 69;


    @BeforeEach
    public void setMockOutput() {
        lenient().when(newspaperRepository.findNewspaperByTitle(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NEWSPAPER_TITLE)){
                Newspaper newspaper=new Newspaper();

                newspaper.setDate(NEWSPAPER_DATE);
                newspaper.setTitle(NEWSPAPER_TITLE);
                newspaper.setNumberOfPages(NEWSPAPER_NUMBEROFPAGES);

                return newspaper;
            }
            return null;
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(newspaperRepository.save(any(Newspaper.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateNewspaper() {
        assertEquals(0, newspaperService.getAllNewspapers().size());
        Newspaper newspaper=null;



    }







}

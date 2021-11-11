package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookingRepository;
import ca.mcgill.ecse321.library.model.Booking;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;





@ExtendWith(MockitoExtension.class)
public class TestBookingService {

    @Mock
    private BookingRepository bookingDao;

    @InjectMocks
    private BookingService bookingService;

    private static final String USER_KEY = "Simo";

    @BeforeEach
    public void setMockOutput() {
    }

    @Test
    public void testCreateBooking(){

    }

    @Test
    public void testGetExistingBooking(){

    }

    @Test
    public void testGetNonExistingBooking(){

    }

    @Test
    public void testCheckOutBook(){

    }

    @Test
    public void testReturnBook(){

    }

    @Test
    public void testDeleteBooking(){

    }

    @Test
    public void testCreateBookingEmpty(){

    }

    @Test
    public void testCreateBookingNoUser(){

    }
}

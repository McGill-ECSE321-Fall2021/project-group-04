package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.ReservationRepository;
import ca.mcgill.ecse321.library.model.Reservation;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class TestReservationService {

    private static final Date NEWSPAPER_DATE = Date.valueOf("2021-11-11");
    private static final String NEWSPAPER_TITLE = "Win";
    private static final Integer NEWSPAPER_NUMBEROFPAGES = 69;
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(reservationService.getAllReservations()).thenAnswer((InvocationOnMock invocation) -> {

            Reservation reservation = new Reservation();
            reservation.setExpirationDate(Date.valueOf("2021-11-15"));

            return reservation;


        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(reservationRepository.save(any(Reservation.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateReservation() {

        Reservation reservation = new Reservation();
        reservation.setExpirationDate(Date.valueOf("2020-11-17"));

        assertNotNull(reservation);
    }
}
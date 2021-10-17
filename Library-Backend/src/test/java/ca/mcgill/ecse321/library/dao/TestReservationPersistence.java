package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;

import ca.mcgill.ecse321.library.model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import ca.mcgill.ecse321.library.model.BookingType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReservationPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ReservationRepository reservationRepository;

    @AfterEach
    public void clearDatabase() {
        reservationRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadReservation() {
        Long id = 6372983L;
        Date expirationDate = Date.valueOf("2021-10-20");

        Reservation reservation = new Reservation();
        reservation.setExpirationDate(expirationDate);
        //reservation.setId(id);

        reservationRepository.save(reservation);
        reservation = null;
        reservation = reservationRepository.findReservationById(id);
        //reservation = reservationRepository.findByExpirationDate(expirationDate);

        assertNotNull(reservation);
        //assertEquals(id, reservation.getId);
        assertEquals(expirationDate, reservation.getExpirationDate());

    }

}

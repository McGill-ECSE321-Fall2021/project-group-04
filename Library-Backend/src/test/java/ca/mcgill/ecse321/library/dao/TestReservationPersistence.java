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
    public void testPersistAndLoadReservationWithID() {
        Date expirationDate = Date.valueOf("2021-10-20");

        Reservation reservation = new Reservation();
        reservation.setExpirationDate(expirationDate);

        reservationRepository.save(reservation);
        Long id = reservation.getId();      //the JPA generates the ID after saving the reservation into the database
        reservation = null;
        reservation = reservationRepository.findReservationById(id);

        assertNotNull(reservation);
        assertEquals(id, reservation.getId());
        assertEquals(expirationDate, reservation.getExpirationDate());

    }

    @Test
    public void testPersistAndLoadReservationWithDate() {
        Date expirationDate = Date.valueOf("2021-10-20");

        Reservation reservation = new Reservation();
        reservation.setExpirationDate(expirationDate);

        reservationRepository.save(reservation);
        Long id = reservation.getId();      //the JPA generates the ID after saving the reservation into the database
        reservation = null;
        reservation = reservationRepository.findByExpirationDate(expirationDate).get(0);

        assertNotNull(reservation);
        assertEquals(id, reservation.getId());
        assertEquals(expirationDate, reservation.getExpirationDate());

    }

}

package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Reservation;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Reservation findReservationById(Long id);

    boolean existsReservationById(Long id);

    List<Reservation> findByExpirationDate(Date expirationDate);
}

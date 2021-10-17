package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Reservation findReservationById(Long id);

    boolean existsReservationById(Long id);
}

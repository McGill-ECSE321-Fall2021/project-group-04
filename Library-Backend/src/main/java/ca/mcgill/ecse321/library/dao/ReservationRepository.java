package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, String>{
	Reservation findReservationByTypeId(Long id);
	boolean existsReservationByTypeId(Long id);
}

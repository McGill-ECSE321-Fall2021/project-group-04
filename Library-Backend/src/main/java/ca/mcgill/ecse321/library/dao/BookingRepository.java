package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, String>{
	Booking findBookingByBookingId(String id);
	boolean existsBookingByBookingId(String id);
}

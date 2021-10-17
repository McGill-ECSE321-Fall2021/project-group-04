package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, String> {
    Booking findBookingByBookingId(Long id);

    boolean existsBookingByBookingId(Long id);
}

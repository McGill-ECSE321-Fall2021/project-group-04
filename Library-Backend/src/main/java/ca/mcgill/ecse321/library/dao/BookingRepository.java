package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.User;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findBookingById(Long id);

    boolean existsBookingById(Long id);

    List<Booking> findByUser(User user);

    List<Booking> findByUserAndBookingDate(User user, Date bookingDate);
}

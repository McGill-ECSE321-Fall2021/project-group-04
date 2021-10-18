package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Lending;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LendingRepository extends CrudRepository<Lending, Long> {
    Lending findLendingById(Long id);

    boolean existsLendingById(Long id);

    List<Lending> findByReturnDate(Date returnDate);
}

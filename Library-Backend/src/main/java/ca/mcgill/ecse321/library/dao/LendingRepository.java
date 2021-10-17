package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Lending;
import org.springframework.data.repository.CrudRepository;

public interface LendingRepository extends CrudRepository<Lending, String> {
    Lending findLendingById(Long id);

    boolean existsLendingById(Long id);
}

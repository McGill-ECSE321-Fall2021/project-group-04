package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Lending;

public interface LendingRepository extends CrudRepository<Lending, String>{
	Lending findLendingByBTypeid(String id);
	boolean existsLendingByBTypeid(String id);
}

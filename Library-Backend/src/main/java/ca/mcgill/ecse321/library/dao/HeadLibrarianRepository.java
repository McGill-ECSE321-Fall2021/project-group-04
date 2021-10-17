package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, String>{
	HeadLibrarian findHeadLibrarianByUsername(String username);
	boolean existsHeadLibrarianByUsername(String username);
}

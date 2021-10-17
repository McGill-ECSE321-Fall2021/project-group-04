package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import org.springframework.data.repository.CrudRepository;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, String> {
    HeadLibrarian findHeadLibrarianByUsername(String username);

    boolean existsHeadLibrarianByUsername(String username);
}

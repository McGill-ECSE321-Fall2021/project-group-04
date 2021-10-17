package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Librarian;
import org.springframework.data.repository.CrudRepository;

public interface LibrarianRepository extends CrudRepository<Librarian, String> {
    Librarian findLibrarianByUsername(String username);

    boolean existsLibrarianByUsername(String username);
}

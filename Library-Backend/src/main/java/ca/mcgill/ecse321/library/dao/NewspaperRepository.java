package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Newspaper;
import org.springframework.data.repository.CrudRepository;

public interface NewspaperRepository extends CrudRepository<Newspaper, String> {
    Newspaper findNewspaperById(Long id);

    boolean existsNewspaperById(Long id);
}

package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Newspaper;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;

public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
    Newspaper findNewspaperById(Long id);

    boolean existsNewspaperById(Long id);

    Newspaper findNewspaperByDate(Date date);

    Newspaper findNewspaperByTitle(String title);
}

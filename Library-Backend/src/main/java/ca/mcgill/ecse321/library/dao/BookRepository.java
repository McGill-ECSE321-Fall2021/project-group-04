package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
    Book findBookByItemId(Long id);

    boolean existsBookByItemId(Long id);
}

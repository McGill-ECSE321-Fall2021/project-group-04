package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{
	Book findBookByItemId(String id);
	boolean existsBookByItemId(String id);
}

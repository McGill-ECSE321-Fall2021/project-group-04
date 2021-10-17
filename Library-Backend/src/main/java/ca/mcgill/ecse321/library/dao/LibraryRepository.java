package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Library;

public interface LibraryRepository extends CrudRepository<Library, String>{
	Library findLibraryByLibraryId(String id);
	boolean existsLibraryByLibraryId(String id);
}

package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, String> {
    Library findLibraryByLibraryId(String id);

    boolean existsLibraryByLibraryId(String id);
}

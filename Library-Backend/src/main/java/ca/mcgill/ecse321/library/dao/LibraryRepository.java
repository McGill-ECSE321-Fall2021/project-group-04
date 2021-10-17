package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Library;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, String> {
    Library findLibraryById(Long id);

    boolean existsLibraryById(Long id);

    Library findLibraryByNameAndAddress(String name, String address);
}

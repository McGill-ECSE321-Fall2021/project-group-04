package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Archive;
import org.springframework.data.repository.CrudRepository;

public interface ArchiveRepository extends CrudRepository<Archive, String> {
    Archive findArchiveById(Long id);

    boolean existsArchiveById(Long id);
}

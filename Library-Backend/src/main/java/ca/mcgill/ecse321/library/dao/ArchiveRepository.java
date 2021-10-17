package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Archive;

public interface ArchiveRepository extends CrudRepository<Archive, String>{
	Archive findArchiveByItemId(String id);
	boolean existsArchiveByItemId(String id);
}

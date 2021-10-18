package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Archive;
import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ArchiveRepository extends CrudRepository<Archive, Long> {
    Archive findArchiveById(Long id);

    boolean existsArchiveById(Long id);

    List<Archive> findByDate(Date date);
    Archive findArchiveByTitle(String title);
}

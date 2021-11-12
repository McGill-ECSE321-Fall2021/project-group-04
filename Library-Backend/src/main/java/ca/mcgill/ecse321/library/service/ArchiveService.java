package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.ArchiveRepository;
import ca.mcgill.ecse321.library.model.Archive;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArchiveService {

    @Autowired
    ArchiveRepository archiveRepository;

    /**
     * @param date
     * @param numberOfPages
     * @param title
     * @return
     * @author alymo
     * Creates an archive
     */
    @Transactional
    public Archive createArchive(String date, String numberOfPages, String title) {

        ImmobileItemServices.checkItemInfo(date, numberOfPages, title);

        Archive archive = new Archive();
        archive.setDate(Date.valueOf(date));
        archive.setNumberOfPages(Integer.valueOf(numberOfPages));
        archive.setTitle(title);


        archiveRepository.save(archive);

        return archive;
    }

    /**
     * @param title
     * @return
     * @author alymo
     * Gets archives by title
     */
    @Transactional
    public Archive getArchiveByTitle(String title) {
        return archiveRepository.findArchiveByTitle(title);
    }

    /**
     * @param title
     * @return
     * @author alymo
     * Deletes archive by title
     */
    @Transactional
    public boolean deleteArchive(String title) {
        Archive archive = archiveRepository.findArchiveByTitle(title);
        if (archive != null) {
            archiveRepository.delete(archive);
            return true;
        } else return false;
    }

    /**
     * @return
     * @author alymo
     * Gets archives from database
     */
    public List<Archive> getAllArchives() {
        return Services.toList(archiveRepository.findAll());
    }


}

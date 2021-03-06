package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Newspaper;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewspaperService {

    @Autowired
    NewspaperRepository newspaperRepository;

    /**
     * @param date
     * @param numberOfPages
     * @param title
     * @return
     * @author alymo
     * Creates a newspaper
     */
    @Transactional
    public Newspaper createNewspaper(String date, String numberOfPages, String title) {

        ImmobileItemServices.checkItemInfo(date, numberOfPages, title);

        Newspaper newspaper = new Newspaper();
        newspaper.setDate(Date.valueOf(date));
        newspaper.setNumberOfPages(Integer.valueOf(numberOfPages));
        newspaper.setTitle(title);

        newspaperRepository.save(newspaper);

        return newspaper;

    }

    @Transactional
    public Newspaper getNewspaperByTitle(String title) {
        return newspaperRepository.findNewspaperByTitle(title);
    }

    @Transactional
    public boolean deleteNewspaper(String title) {
        Newspaper newspaper = newspaperRepository.findNewspaperByTitle(title);
        if (newspaper != null) {
            newspaperRepository.delete(newspaper);
            return true;
        }
        return false;
    }

    public List<Newspaper> getAllNewspapers() {
        return Services.toList(newspaperRepository.findAll());
    }


}

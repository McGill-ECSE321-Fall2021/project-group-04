package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class HeadLibrarianService {

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;

    // TODO
    @Transactional
    public HeadLibrarian createHeadLibrarian(String aUsername, String aPassword, String aAddress) {
        return null;
    }

    // TODO
    @Transactional
    public boolean deleteHeadLibrarian(String username) {
        return true;
    }

    // TODO
    @Transactional
    public HeadLibrarian getHeadLibrarian(String username) {
        return null;
    }

}

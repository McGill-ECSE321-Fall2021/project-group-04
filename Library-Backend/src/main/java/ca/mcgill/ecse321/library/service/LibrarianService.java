package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    LibrarianRepository librarianRepository;

    // TODO
    @Transactional
    public Librarian createLibrarian(String aUsername, String aPassword, String aAddress) {
        return null;
    }

    // TODO
    @Transactional
    public boolean deleteLibrarian(String username) {
        return true;
    }

    // TODO
    @Transactional
    public Librarian getLibrarian(String username) {
        return null;
    }

    // TODO
    @Transactional
    public List<Librarian> getAllLibrarians() {
        return null;
    }

}

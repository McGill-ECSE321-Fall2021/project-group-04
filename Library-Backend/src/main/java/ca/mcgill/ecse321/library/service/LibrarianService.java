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

    /**
     * @author Abd-El-Aziz Zayed
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     */
    @Transactional
    public Librarian createLibrarian(String aUsername, String aPassword, String aAddress) {
        UserService.checkValidUsername(aUsername);
        UserService.checkValidPassword(aPassword);
        UserService.checkValidAddress(aAddress);

        Librarian librarian = new Librarian();
        librarian.setUsername(aUsername);
        librarian.setPassword(aPassword);
        librarian.setAddress(aAddress);

        librarianRepository.save(librarian);

        return librarian;
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @param username
     * @param newPassword
     * @return
     */
    @Transactional
    public Librarian changeLibrarianPassword(String username, String newPassword) {
        UserService.checkValidUsername(username);
        Librarian librarian = librarianRepository.findLibrarianByUsername(username);

        if (librarian == null) {
            throw new IllegalArgumentException("Librarian is not found.");
        }

        if (UserService.checkValidPassword(newPassword)) {
            librarian.setPassword(newPassword);
        }
        librarianRepository.save(librarian);
        return librarian;
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @param username
     * @return
     */
    @Transactional
    public boolean deleteLibrarian(String username) {
        Librarian librarian = getLibrarian(username);
        if (librarian == null) {
            throw new IllegalArgumentException("Librarian is not found.");
        }
        librarianRepository.delete(librarian);
        return true;
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @param username
     * @return
     */
    @Transactional
    public Librarian getLibrarian(String username) {
        return librarianRepository.findLibrarianByUsername(username);
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @return
     */
    @Transactional
    public List<Librarian> getAllLibrarians() {
        return Services.toList(librarianRepository.findAll());
    }

}

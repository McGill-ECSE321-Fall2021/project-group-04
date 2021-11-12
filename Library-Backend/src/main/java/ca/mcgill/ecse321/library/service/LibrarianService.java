package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibrarianService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LibrarianRepository librarianRepository;

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;

    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public Librarian createLibrarian(String aUsername, String aPassword, String aAddress) {
        checkValidUsername(aUsername);
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
     * @param username
     * @param newPassword
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public Librarian changeLibrarianPassword(String username, String newPassword) {
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
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
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
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public Librarian getLibrarian(String username) {
        return librarianRepository.findLibrarianByUsername(username);
    }

    /**
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @Transactional
    public List<Librarian> getAllLibrarians() {
        return Services.toList(librarianRepository.findAll());
    }

    /**
     * @param username
     * @return
     * @author Jewoo Lee
     */
    public boolean checkValidUsername(String username) {
        if (username == null || username == "") {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (librarianRepository.findLibrarianByUsername(username) == null) {
            return true;
        }
        throw new IllegalArgumentException("Username already exists.");
    }

    /**
     * @param username
     * @param password
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public Librarian login(String username, String password) {
        if (librarianRepository.findLibrarianByUsername(username) == null) {
            throw new IllegalArgumentException("Invalid Username.");
        }

        Librarian librarian = librarianRepository.findLibrarianByUsername(username);
        if (librarian != null && librarian.getPassword().equals(password)) {
            return librarian;
        }

        throw new IllegalArgumentException("Incorrect Password.");
    }
}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class HeadLibrarianService {

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;

    @Transactional
    public HeadLibrarian createHeadLibrarian(String aUsername, String aPassword, String aAddress) {
        UserService.checkValidUsername(aUsername);
        UserService.checkValidPassword(aPassword);
        UserService.checkValidAddress(aAddress);

        HeadLibrarian librarian = new HeadLibrarian();
        librarian.setUsername(aUsername);
        librarian.setPassword(aPassword);
        librarian.setAddress(aAddress);

        headLibrarianRepository.save(librarian);

        return librarian;
    }

    @Transactional
    public HeadLibrarian changeHeadLibrarianPassword(String username, String newPassword) {
        UserService.checkValidUsername(username);
        HeadLibrarian librarian = headLibrarianRepository.findHeadLibrarianByUsername(username);

        if (librarian == null) {
            throw new IllegalArgumentException("Head librarian is not found.");
        }

        if (UserService.checkValidPassword(newPassword)) {
            librarian.setPassword(newPassword);
        }
        headLibrarianRepository.save(librarian);
        return librarian;
    }

    @Transactional
    public boolean deleteHeadLibrarian(String username) {
        HeadLibrarian librarian = getHeadLibrarian(username);
        if (librarian == null) {
            throw new IllegalArgumentException("Head librarian is not found.");
        }
        headLibrarianRepository.delete(librarian);
        return true;
    }

    @Transactional
    public HeadLibrarian getHeadLibrarian(String username) {
        return headLibrarianRepository.findHeadLibrarianByUsername(username);
    }

}

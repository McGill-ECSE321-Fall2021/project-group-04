package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.service.LibrarianService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianController {

    @Autowired
    private LibrarianService libraryService;

    /**
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @GetMapping(value = {"/librarian/{username}"})
    public LibrarianDto viewLibrarian(@PathVariable("username") String username) {
        return DTOConverter.convertToDto(libraryService.getLibrarian(username));
    }


    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @PostMapping(value = {"/create_librarian"})
    public ResponseEntity<?> createLibrarian(@RequestParam("username") String aUsername,
                                             @RequestParam("password") String aPassword,
                                             @RequestParam("address") String aAddress) {
        try {
            Librarian librarian = libraryService.createLibrarian(aUsername, aPassword, aAddress);
            return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @GetMapping(value = {"/librarians"})
    public List<LibrarianDto> getAllLibrarians() {
        return libraryService.getAllLibrarians().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }

    @PostMapping(value = {"/librarian_login", "/librarian_login/"})
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Librarian librarian = null;
        try {
            librarian = libraryService.login(username, password);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.OK);
    }


    @PutMapping(value = {"/assign_schedule", "/assign_schedule/"})
    public ResponseEntity<?> assignSchedule(@RequestParam String startTime, @RequestParam String endTime, @RequestParam String librarianUsername) {
        Librarian librarian = null;
        try {
            assignSchedule(startTime, endTime, librarianUsername);
            librarian = libraryService.getLibrarian(librarianUsername);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.OK);
    }

}

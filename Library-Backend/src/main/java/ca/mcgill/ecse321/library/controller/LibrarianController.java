package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.MemberDto;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.service.LibrarianService;
import ca.mcgill.ecse321.library.service.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


public class LibrarianController {

    @Autowired
    private LibrarianService libraryService;

    /**
     * @author Abd-El-Aziz Zayed
     * @param username
     * @return
     */
    @GetMapping(value = {"/librarian/{username}"})
    public LibrarianDto viewLibrarian(@PathVariable("username") String username) {
        return DTOConverter.convertToDto(libraryService.getLibrarian(username));
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     */
    @PostMapping(value = {"/create_librarian"})
    public ResponseEntity<?> createLibrarian(@RequestParam("username") String aUsername,
                                          @RequestParam("password") String aPassword,
                                          @RequestParam("address") String aAddress) {
        try {
            Librarian librarian = libraryService.createLibrarian(aUsername, aPassword, aAddress);
            return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.CREATED) ;
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @return
     */
    @GetMapping(value = {"/librarians"})
    public List<LibrarianDto> getAllLibrarians() {
        return libraryService.getAllLibrarians().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }

}

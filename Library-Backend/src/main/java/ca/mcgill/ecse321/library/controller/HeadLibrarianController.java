package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import ca.mcgill.ecse321.library.service.LibrarianService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class HeadLibrarianController {

    @Autowired
    private HeadLibrarianService headLibrarianService;

    @GetMapping(value = {"/head_librarian/{username}"})
    public LibrarianDto viewHeadLibrarian(@PathVariable("username") String username) {
        return DTOConverter.convertToDto(headLibrarianService.getHeadLibrarian(username));
    }

    @PostMapping(value = {"/create_head_librarian"})
    public ResponseEntity<?> createMember(@RequestParam("username") String aUsername,
                                          @RequestParam("password") String aPassword,
                                          @RequestParam("address") String aAddress) {
        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(aUsername, aPassword, aAddress);
            return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.CREATED) ;
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

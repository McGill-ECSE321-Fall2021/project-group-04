package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class UserController {




    @Autowired
    private UserService userService;

    /**
     * @author Jewoo Lee
     * @param username
     * @param password
     * @return
     */
    @PostMapping(value = {"/login", "/login/"})
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        User user = null;
        try {
            user = userService.login(username, password);
        }
        catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(user instanceof Member) {
            return new ResponseEntity<>(DTOConverter.convertToDto((Member) user), HttpStatus.OK);
        }

        if(user instanceof Librarian) {
            return new ResponseEntity<>(DTOConverter.convertToDto((Librarian) user), HttpStatus.OK);
        }

        if(user instanceof HeadLibrarian) {
            return new ResponseEntity<>(DTOConverter.convertToDto((HeadLibrarian) user), HttpStatus.OK);
        }

        return null;
    }

}

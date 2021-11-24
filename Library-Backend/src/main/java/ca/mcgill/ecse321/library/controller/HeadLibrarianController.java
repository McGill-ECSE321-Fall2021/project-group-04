package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import java.sql.Time;
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


@CrossOrigin(origins = "*")
@RestController
public class HeadLibrarianController {

    @Autowired
    private HeadLibrarianService headLibrarianService;

    /**
     * @param username
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @GetMapping(value = {"/head_librarian/{username}"})
    public LibrarianDto viewHeadLibrarian(@PathVariable("username") String username) {
        return DTOConverter.convertToDto(headLibrarianService.getHeadLibrarian(username));
    }

    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     * @author Abd-El-Aziz Zayed
     */
    @PostMapping(value = {"/create_head_librarian"})
    public ResponseEntity<?> createMember(@RequestParam("username") String aUsername,
                                          @RequestParam("password") String aPassword,
                                          @RequestParam("address") String aAddress) {
        try {
            Librarian librarian = headLibrarianService.createHeadLibrarian(aUsername, aPassword, aAddress);
            return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param aWorkday
     * @param aStartTime
     * @param anEndTime
     * @return
     * @author Saghar Sahebi
     */

    @PostMapping(value = {"/schedule"})
    public ResponseEntity<?> creatSchedule(@RequestParam("workday") String aWorkday,
                                           @RequestParam("start_time") String aStartTime,
                                           @RequestParam("end_time") String anEndTime) {
        try {
            WorkDay.DayOfWeek workday = WorkDay.DayOfWeek.valueOf(aWorkday);
            Time startTime = Time.valueOf(aStartTime + ":00");
            Time endTime = Time.valueOf(anEndTime + ":00");

            WorkDay workSchedule = headLibrarianService.Schedule(workday, startTime, endTime);
            return new ResponseEntity<>(DTOConverter.convertToDto(workSchedule), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @return
     * @author Saghar Sahebi
     */

    @GetMapping(value = {"/head_librarians"})
    public List<LibrarianDto> getAllHeadLibrarians() {
        return headLibrarianService.getAllHeadLibrarians().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }

    @PostMapping(value = {"/head_librarian_login", "/head_librarian_login/"})
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        HeadLibrarian librarian = null;
        try {
            librarian = headLibrarianService.login(username, password);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(DTOConverter.convertToDto(librarian), HttpStatus.OK);
    }
}

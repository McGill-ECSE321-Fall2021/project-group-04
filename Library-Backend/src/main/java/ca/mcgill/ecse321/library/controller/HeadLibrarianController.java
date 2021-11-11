package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.WorkDayDto;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.model.WorkDay.DayOfWeek;
import ca.mcgill.ecse321.library.service.HeadLibrarianService;
import ca.mcgill.ecse321.library.service.LibrarianService;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
public class HeadLibrarianController {

    @Autowired
    private HeadLibrarianService headLibrarianService;

    /**
     * @author Abd-El-Aziz Zayed
     * @param username
     * @return
     */
    @GetMapping(value = {"/head_librarian/{username}"})
    public LibrarianDto viewHeadLibrarian(@PathVariable("username") String username) {
        return DTOConverter.convertToDto(headLibrarianService.getHeadLibrarian(username));
    }

    /**
     * @author Abd-El-Aziz Zayed
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @return
     */
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
    
    /**
     * @author Saghar Sahebi
     * 
     * @param aWorkday
     * @param aStartTime
     * @param anEndTime
     * @return
     */
    
    @PostMapping(value = {"/schedule"})
    public ResponseEntity<?> creatSchedule(@RequestParam("workday") String aWorkday,
    									   @RequestParam("starttime") String aStartTime,
    									   @RequestParam ("endTime") String anEndTime) {
		try {
			DayOfWeek workday = WorkDay.DayOfWeek.valueOf(aWorkday);
			Time startTime = Time.valueOf(aStartTime + ":00");
			Time endTime = Time.valueOf(anEndTime + ":00" );
			
			WorkDay workschedule = headLibrarianService.Schedule(workday, startTime, endTime);
			return new ResponseEntity<>(DTOConverter.convertToDto(workschedule), HttpStatus.CREATED) ;
		}
    	catch(IllegalArgumentException e) {
    		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    
    
    /**
     * @author Saghar Sahebi
     * @return
     */
    
    @GetMapping(value = {"/head_librarians"})
    public List<LibrarianDto> getAllHeadLibrarians() {
        return headLibrarianService.getAllHeadLibrarians().stream().map(DTOConverter::convertToDto).collect(Collectors.toList());
    }
    

}

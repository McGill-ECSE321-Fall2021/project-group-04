package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.dao.WorkDayRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.model.WorkDay.DayOfWeek;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadLibrarianService {

    @Autowired
    HeadLibrarianRepository headLibrarianRepository;
   
    @Autowired
	WorkDayRepository workdayRepository;

	/**
	 * @author Abd-El-Aziz Zayed
	 * @param aUsername
	 * @param aPassword
	 * @param aAddress
	 * @return
	 */
    @Transactional
    public HeadLibrarian createHeadLibrarian(String aUsername, String aPassword, String aAddress) {
        checkValidUsername(aUsername);
        UserService.checkValidPassword(aPassword);
        UserService.checkValidAddress(aAddress);

        HeadLibrarian librarian = new HeadLibrarian();
        librarian.setUsername(aUsername);
        librarian.setPassword(aPassword);
        librarian.setAddress(aAddress);

        headLibrarianRepository.save(librarian);

        return librarian;
    }

	/**
	 * @author Abd-El-Aziz Zayed
	 * @param username
	 * @param newPassword
	 * @return
	 */
    @Transactional
    public HeadLibrarian changeHeadLibrarianPassword(String username, String newPassword) {
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

	/**
	 * @author Abd-El-Aziz Zayed
	 * @param username
	 * @return
	 */
    @Transactional
    public boolean deleteHeadLibrarian(String username) {
        HeadLibrarian librarian = getHeadLibrarian(username);
        if (librarian == null) {
            throw new IllegalArgumentException("Head librarian is not found.");
        }
        headLibrarianRepository.delete(librarian);
        return true;
    }

	/**
	 * @author Abd-El-Aziz Zayed
	 * @param username
	 * @return
	 */
    @Transactional
    public HeadLibrarian getHeadLibrarian(String username) {
        return headLibrarianRepository.findHeadLibrarianByUsername(username);
    }
    
    //list of all head librarians
    @Transactional
    public List<HeadLibrarian> getAllHeadLibrarians(){
    	return Services.toList(headLibrarianRepository.findAll());
	}

    /**
	 * HeadLib can assign schedule
	 * 
	 * @param workday
	 * @param startTime
	 * @param endTime
	 * @param librarianUser
	 * @param headlibUser
	 * 
	 * @return assigned schedule
	 */
	@Transactional
	public Set<WorkDay> AssignSchedule (DayOfWeek workday,Time startTime, Time endTime, String librarianUser, String headlibUser){
	
		HeadLibrarianService headlibrarian = new HeadLibrarianService();
		LibrarianService librarian = new LibrarianService();
	
	
		if(headlibrarian.getAllHeadLibrarians().contains(headLibrarianRepository.findHeadLibrarianByUsername(headlibUser))){
			librarian.getLibrarian(librarianUser).setWorkHours(WeekSchedule(Schedule(workday,startTime,endTime)));
		}
	
		else {
			throw new IllegalArgumentException("Only a headlibrarian can assign schedules.");
		}
	
		workdayRepository.saveAll(librarian.getLibrarian(librarianUser).getWorkHours());
		
		return librarian.getLibrarian(librarianUser).getWorkHours();
		
		}
	
	/**
	 * HeadLib can delete a schedule
	 * 
	 * @param workday
	 * @param startTime
	 * @param endTime
	 * @param librarianUser
	 * @param headlibUser
	 * 
	 * @return assigned schedule
	 */
	@Transactional
	public void DeleteSchedule(DayOfWeek workday,Time startTime, Time endTime ,String librarianUser, String headlibUser){
		
		HeadLibrarianService headlibrarian = new HeadLibrarianService();
		LibrarianService librarian = new LibrarianService();
		
		if(librarian.getLibrarian(librarianUser).getWorkHours().isEmpty()) {
			throw new IllegalArgumentException("No schedule exists for this librarian.");
		}
		
		if(headlibrarian.getAllHeadLibrarians().contains(headLibrarianRepository.findHeadLibrarianByUsername(headlibUser))) {
			throw new IllegalArgumentException("Only a headlibrarian can assign schedules.");
		}
		
		else {
		workdayRepository.deleteAll(librarian.getLibrarian(librarianUser).getWorkHours());
		
		}
		
	}
	


	@Transactional
	public WorkDay Schedule(DayOfWeek workday, Time astartTime, Time aendTime) {
		WorkDay workdays = new WorkDay();
		workdays.setDayOfWeek(workday);
		workdays.setStartTime(astartTime);
		workdays.setEndTime(aendTime);
		return workdays;
	
	}

	@Transactional
	public Set<WorkDay> WeekSchedule (WorkDay aWorkDaySchedule){
		Set<WorkDay> theSchedule = new HashSet<WorkDay>();  
		theSchedule.add(aWorkDaySchedule);
		return theSchedule;
	}

	/**
	 * @author Jewoo Lee
	 * @param username
	 * @return
	 */
	public boolean checkValidUsername(String username) {
		if (username == null || username == "") {
			throw new IllegalArgumentException("Username cannot be empty.");
		}
		if (headLibrarianRepository.findHeadLibrarianByUsername(username) == null) {
			return true;
		}
		throw new IllegalArgumentException("Username already exists.");
	}

	/**
	 * @author Jewoo Lee
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public Librarian login(String username, String password) {
		if (!(headLibrarianRepository.findHeadLibrarianByUsername(username) == null)) {
			throw new IllegalArgumentException("Invalid Username.");
		}

		HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByUsername(username);
		if (headLibrarian != null && headLibrarian.getPassword().equals(password)) {
			return headLibrarian;
		}

		throw new IllegalArgumentException("Incorrect Password.");
	}
}

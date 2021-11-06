package ca.mcgill.ecse321.library.service;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.WorkDayRepository;
import ca.mcgill.ecse321.library.model.WorkDay;
import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.dao.LibraryRepository;

import ca.mcgill.ecse321.library.model.User;

/**
 * @author Saghar Sahebi
 */
public class AssignScheduleService {
	@Autowired
	WorkDayRepository workdayRepository;
	
	@Autowired
	HeadLibrarianRepository headlibrarianRepository;
	
	@Autowired
	LibrarianRepository librarianRepository;
	
	@Autowired
	LibraryRepository libraryRepository;
	
	/**
	 * checks if the user is a librarian
	 * @param User 
	 * 
	 * @return the librarian
	 */
	@Transactional
	public Librarian getLibrarian (User user) {
		Librarian librarian = librarianRepository.findLibrarianById(user.getId());
		
		if(librarian == null) 
			throw new IllegalArgumentException("Librarian not found.");
		else
		return librarian;
	}
	
	/**
	 * checks if the user is a headlibrarian
	 * @param User 
	 * 
	 * @return the headlibrarian
	 */
	@Transactional
	public HeadLibrarian getHeadLibrarian(User user) {
		HeadLibrarian headlibrarian = headlibrarianRepository.findHeadLibrarianById(user.getId());
		if(headlibrarian == null) 
			throw new IllegalArgumentException("librarian not found.");
		else
			return headlibrarian;
		
	}
	
	/**
	 * assigns a schedule to a librarian 
	 * 
	 * @param user1
	 * @param user2 
	 * 
	 * @return assigned schedule
	 */
	
	@Transactional
	public Set<WorkDay> AssignScheduleTolib(User user1,User user2) {
		
		getHeadLibrarian(user1);
		
		Set<WorkDay> libWorkHours = getLibrarian(user2).getWorkHours();
		if(libWorkHours == null)
			throw new IllegalArgumentException("Librarian not available.");
		
		for(int i=0; i < libWorkHours.size(); i++) {
			getHeadLibrarian(user1).setWorkHours(libWorkHours);
		}
		return libWorkHours;
		
		
	}
	
	/**
	 * assigns a schedule to a headlibrarian 
	 * 
	 * @param user1
	 * 
	 * @return assigned schedule
	 */
	
	@Transactional
	public Set<WorkDay> AssignScheduleToHead(User user1) {
		
		getHeadLibrarian(user1);
		Set<WorkDay> headlibWorkHours = getHeadLibrarian(user1).getWorkHours();
		
		if(headlibWorkHours == null)
			throw new IllegalArgumentException("HeadLibrarian not available.");
		
		for(int i=0; i < headlibWorkHours.size(); i++) {
			getHeadLibrarian(user1).setWorkHours(headlibWorkHours);
		}
		return headlibWorkHours;
	
		
	}

	
	
}
	
	
	
		
		
	




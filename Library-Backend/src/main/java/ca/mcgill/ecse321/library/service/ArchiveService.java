package ca.mcgill.ecse321.library.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.ArchiveRepository;
import ca.mcgill.ecse321.library.model.Archive;





public class ArchiveService {
	
	@Autowired
	ArchiveRepository archiveRepository;
	
	/**
	 * @author alymo
	 * Creates a book
	 * @param barCode
	 * @param title
	 * @param author
	 * @param dateOfRelease
	 * @param price
	 * @param isbn
	 * @param numberOfPages
	 * @return
	 */
	@Transactional
	public Archive createArchive(String date, String numberOfPages, String title) {
		
		HelpersBooking.checkImmobileItemInfo(date, numberOfPages, title);
		

		Archive archive = new Archive();
		archive.setDate(Date.valueOf(date));
		archive.setNumberOfPages(Integer.valueOf(numberOfPages));
		archive.setTitle(title);
		
		
		archiveRepository.save(archive);
		
		return archive;
		
	}
	
	
	
}

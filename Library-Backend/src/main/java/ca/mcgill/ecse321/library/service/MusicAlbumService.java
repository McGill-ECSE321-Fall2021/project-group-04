package ca.mcgill.ecse321.library.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.library.model.MusicAlbum;

public class MusicAlbumService {
	
	@Autowired
	MusicAlbumRepository musicAlbumRepository;
	
	/**
	 * @author alymo
	 * Creates a music album
	 * @param barCode
	 * @param title
	 * @param author
	 * @param dateOfRelease
	 * @param price
	 * @param numberOfSongs
	 * @param totalLength
	 * @return
	 */
	@Transactional
	public MusicAlbum createmusicAlbum(String barCode, String title, String author,
			String dateOfRelease, String price, String numberOfSongs, String totalLength) {
		
		HelpersBooking.checkMobileItemInfo(barCode, title, author, dateOfRelease, price);
		
		String error = "";
		if(numberOfSongs == null || numberOfSongs == "") {
			error += "number of songs needs to be specified ";

		}		
		if(totalLength == null || totalLength == "") {
			error += "total length needs to be specified ";
		}
		
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);

		MusicAlbum musicAlbum = new MusicAlbum();
		musicAlbum.setBarcode(barCode);
		musicAlbum.setTitle(title);
		musicAlbum.setAuthor(author);
		musicAlbum.setDateOfRelease(Date.valueOf(dateOfRelease));
		musicAlbum.setPrice(Integer.valueOf(price));
		musicAlbum.setNumberOfSongs(Integer.valueOf(numberOfSongs));
		musicAlbum.setTotalLength(Integer.valueOf(totalLength));
		musicAlbum.setBooking(null);
		
		musicAlbumRepository.save(musicAlbum);
		
		return musicAlbum;
		
	}
	
	
	
}



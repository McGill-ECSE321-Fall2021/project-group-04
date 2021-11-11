package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.MusicAlbum;

@Service
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
	public MusicAlbum createMusicAlbum(String barCode, String title, String author,
			String dateOfRelease, String price, String numberOfSongs, String totalLength) {

		MobileItemServices.checkItemInfo(barCode, title, author, dateOfRelease, price);
		
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
	
	@Transactional
	public MusicAlbum getBookByTitle(String title) {
		return musicAlbumRepository.findMusicAlbumByTitle(title);
	}
	
	
	@Transactional
	public boolean deleteBook(String title) {
		MusicAlbum musicAlbum = musicAlbumRepository.findMusicAlbumByTitle(title);
		if(musicAlbum!=null) {
			musicAlbumRepository.delete(musicAlbum);
			return true;
		}
		else return false;
	}
	
	public List<MusicAlbum> getAllBooks(){
		return Services.toList(musicAlbumRepository.findAll());
	}
	
	
	
	
}



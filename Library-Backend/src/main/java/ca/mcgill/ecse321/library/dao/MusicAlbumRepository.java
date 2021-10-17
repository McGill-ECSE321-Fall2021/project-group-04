package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.MusicAlbum;

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, String>{
	MusicAlbum findMusicAlbumByItemId(String id);
	boolean existsMusicAlbumByItemId(String id);
}

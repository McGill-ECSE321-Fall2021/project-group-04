package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.MusicAlbum;
import org.springframework.data.repository.CrudRepository;

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, String> {
    MusicAlbum findMusicAlbumByItemId(Long id);

    boolean existsMusicAlbumByItemId(Long id);
}

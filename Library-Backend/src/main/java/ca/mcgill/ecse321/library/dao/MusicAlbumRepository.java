package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, String> {
    MusicAlbum findMusicAlbumById(Long id);

    boolean existsMusicAlbumById(Long id);

    List<MusicAlbum> findByBooking(Booking booking);

    MusicAlbum findMusicAlbumByBarcode(String barcode);

    MusicAlbum findMusicAlbumByTitle(String title);

    List<MusicAlbum> findByAuthor(String author);
}

package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.*;
import ca.mcgill.ecse321.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//@CrossOrigin(origins = "*")
//@RestController
public class LibraryItemController {

    @Autowired
    BookService bookService;

    @Autowired
    MovieService movieService;

    @Autowired
    MusicAlbumService musicAlbumService;

    @Autowired
    ArchiveService archiveService;

    @Autowired
    NewspaperService newspaperService;

    /**
     * @author alymo
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param isbn
     * @param numberOfPages
     * @return
     */
    @PostMapping(value = {"/create_book"})
    public ResponseEntity<?> createBook(@RequestParam String barCode,@RequestParam String title,@RequestParam String author,
                                        @RequestParam String dateOfRelease,@RequestParam String price,@RequestParam String isbn,@RequestParam String numberOfPages){
        Book book = null;
        try{
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
            return new ResponseEntity<>(DTOConverter.convertToDto(book, book.getBooking()), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * @author alymo
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param length
     * @return
     */
    @PostMapping(value = {"/create_movie"})
    public ResponseEntity<?> createMovie(@RequestParam String barCode,@RequestParam String title,@RequestParam String author,
                                        @RequestParam String dateOfRelease,@RequestParam String price,@RequestParam String length){
         Movie movie = null;
        try{
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
            return new ResponseEntity<>(DTOConverter.convertToDto(movie, movie.getBooking()), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author alymo
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param numberOfSongs
     * @param totalLength
     * @return
     */
    @PostMapping(value = {"/create_music_album"})
    public ResponseEntity<?> createMusicAlbum(@RequestParam String barCode,@RequestParam String title,@RequestParam String author,
                                         @RequestParam String dateOfRelease,@RequestParam String price,@RequestParam String numberOfSongs, @RequestParam String totalLength){
        MusicAlbum musicAlbum = null;
        try{
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, totalLength);
            return new ResponseEntity<>(DTOConverter.convertToDto(musicAlbum, musicAlbum.getBooking()), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author alymo
     * @param date
     * @param numofPages
     * @param title
     * @return
     */
    @PostMapping(value = {"/create_archive"})
    public ResponseEntity<?> createArchive(@RequestParam String date,@RequestParam String numofPages,@RequestParam String title){
        Archive archive= null;
        try{
            archive = archiveService.createArchive(date, numofPages, title);
            return new ResponseEntity<>(DTOConverter.convertToDto(archive), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author alymo
     * @param date
     * @param numofPages
     * @param title
     * @return
     */
    @PostMapping(value = {"/create_newspaper"})
    public ResponseEntity<?> createNewspaper(@RequestParam String date,@RequestParam String numofPages,@RequestParam String title){
        Newspaper newspaper = null;
        try{
            newspaper = newspaperService.createNewspaper(date, numofPages, title);
            return new ResponseEntity<>(DTOConverter.convertToDto(newspaper), HttpStatus.CREATED);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

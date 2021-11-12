package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ArchiveDto;
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.MusicAlbumDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.model.Archive;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.service.ArchiveService;
import ca.mcgill.ecse321.library.service.BookService;
import ca.mcgill.ecse321.library.service.MovieService;
import ca.mcgill.ecse321.library.service.MusicAlbumService;
import ca.mcgill.ecse321.library.service.NewspaperService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
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
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param isbn
     * @param numberOfPages
     * @return
     * @author alymo
     */
    @PostMapping(value = {"/create_book"})
    public ResponseEntity<?> createBook(@RequestParam String barCode, @RequestParam String title, @RequestParam String author,
                                        @RequestParam String dateOfRelease, @RequestParam String price, @RequestParam String isbn, @RequestParam String numberOfPages) {
        Book book = null;
        try {
            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
            return new ResponseEntity<>(DTOConverter.convertToDto(book, book.getBooking()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param length
     * @return
     * @author alymo
     */
    @PostMapping(value = {"/create_movie"})
    public ResponseEntity<?> createMovie(@RequestParam String barCode, @RequestParam String title, @RequestParam String author,
                                         @RequestParam String dateOfRelease, @RequestParam String price, @RequestParam String length) {
        Movie movie = null;
        try {
            movie = movieService.createMovie(barCode, title, author, dateOfRelease, price, length);
            return new ResponseEntity<>(DTOConverter.convertToDto(movie, movie.getBooking()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @param numberOfSongs
     * @param totalLength
     * @return
     * @author alymo
     */
    @PostMapping(value = {"/create_music_album"})
    public ResponseEntity<?> createMusicAlbum(@RequestParam String barCode, @RequestParam String title, @RequestParam String author,
                                              @RequestParam String dateOfRelease, @RequestParam String price, @RequestParam String numberOfSongs, @RequestParam String totalLength) {
        MusicAlbum musicAlbum = null;
        try {
            musicAlbum = musicAlbumService.createMusicAlbum(barCode, title, author, dateOfRelease, price, numberOfSongs, totalLength);
            return new ResponseEntity<>(DTOConverter.convertToDto(musicAlbum, musicAlbum.getBooking()), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param date
     * @param numofPages
     * @param title
     * @return
     * @author alymo
     */
    @PostMapping(value = {"/create_archive"})
    public ResponseEntity<?> createArchive(@RequestParam String date, @RequestParam String numofPages, @RequestParam String title) {
        Archive archive = null;
        try {
            archive = archiveService.createArchive(date, numofPages, title);
            return new ResponseEntity<>(DTOConverter.convertToDto(archive), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param date
     * @param numofPages
     * @param title
     * @return
     * @author alymo
     */
    @PostMapping(value = {"/create_newspaper"})
    public ResponseEntity<?> createNewspaper(@RequestParam String date, @RequestParam String numofPages, @RequestParam String title) {
        Newspaper newspaper = null;
        try {
            newspaper = newspaperService.createNewspaper(date, numofPages, title);
            return new ResponseEntity<>(DTOConverter.convertToDto(newspaper), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return list of all the books
     * @author Alexandre
     * Gets a list of all the books
     */
    @GetMapping(value = {"/books", "/books/"})
    public List<BookDto> getAllBooks() {
        List<BookDto> books = new ArrayList<BookDto>();
        for (Book book : bookService.getAllBooks()) {
            books.add(DTOConverter.convertToDto(book, book.getBooking()));
        }
        return books;
    }

    /**
     * @return list of all the movies
     * @author Alexandre
     * Gets a list of all the movies
     */
    @GetMapping(value = {"/movies", "/movies/"})
    public List<MovieDto> getAllMovies() {
        List<MovieDto> movies = new ArrayList<MovieDto>();
        for (Movie movie : movieService.getAllMovies()) {
            movies.add(DTOConverter.convertToDto(movie, movie.getBooking()));
        }
        return movies;
    }

    /**
     * @return list of all the Music Albums
     * @author Alexandre
     * Gets a list of all the Music Albums
     */
    @GetMapping(value = {"/musicAlbums", "/musicAlbums/"})
    public List<MusicAlbumDto> getAllMusicAlbums() {
        List<MusicAlbumDto> musicAlbums = new ArrayList<MusicAlbumDto>();
        for (MusicAlbum musicAlbum : musicAlbumService.getAllMusicAlbums()) {
            musicAlbums.add(DTOConverter.convertToDto(musicAlbum, musicAlbum.getBooking()));
        }
        return musicAlbums;
    }

    /**
     * @return list of all the Archive
     * @author Alexandre
     * Gets a list of all the Archive
     */
    @GetMapping(value = {"/archive", "/archive/"})
    public List<ArchiveDto> getAllArchives() {
        List<ArchiveDto> archives = new ArrayList<ArchiveDto>();
        for (Archive archive : archiveService.getAllArchives()) {
            archives.add(DTOConverter.convertToDto(archive));
        }
        return archives;
    }

    /**
     * @return list of all the Newspapers
     * @author Alexandre
     * Gets a list of all the Newpapers
     */
    @GetMapping(value = {"/newspaper", "/newspaper/"})
    public List<NewspaperDto> getAllNewspapers() {
        List<NewspaperDto> newspapers = new ArrayList<NewspaperDto>();
        for (Newspaper newspaper : newspaperService.getAllNewspapers()) {
            newspapers.add(DTOConverter.convertToDto(newspaper));
        }
        return newspapers;
    }


}

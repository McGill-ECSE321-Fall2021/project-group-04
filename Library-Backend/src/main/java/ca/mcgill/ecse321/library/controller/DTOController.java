package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.*;
import ca.mcgill.ecse321.library.model.*;

public class DTOController {
    public static BookingTypeDto convertToDto(BookingType bt){
        //need to differentiate between lending and reservation
        if(bt instanceof Lending){
            return new LendingDto(bt.getId(),((Lending) bt).getReturnDate()); //return LendingDto
        }
        else{
            return new ReservationDto(bt.getId(),((Reservation)bt).getExpirationDate()); //return ReservationDto
        }
    }

    public static UserDto convertToDto(User user){
        //not taking into consideration different types of users
        return new UserDto(user.getId(),user.getUsername(), user.getPassword(), user.getAddress());
    }

    public static BookingDto convertToDto(Booking b, User aUser, BookingType bt){
        BookingTypeDto btDto = convertToDto(bt);
        UserDto userDto = convertToDto(aUser);

        return new BookingDto(b.getId(),b.getBookingDate(),userDto,btDto);
    }

    public static BookDto convertToDto(Book book, Booking booking){

        BookingDto bookingDto = convertToDto(booking, booking.getUser(), booking.getBookingType());
        BookDto bookDto = new BookDto(book.getId(), book.getBarcode(), book.getTitle(), book.getAuthor(),
                book.getDateOfRelease(), book.getPrice(), book.getIsbn(), book.getNumberOfPages(), bookingDto);
        return bookDto;
    }

    public static MovieDto convertToDto(Movie movie, Booking booking){

        BookingDto bookingDto = convertToDto(booking, booking.getUser(), booking.getBookingType());
        MovieDto bookDto = new MovieDto(movie.getId(), movie.getBarcode(), movie.getTitle(), movie.getAuthor(),
                movie.getDateOfRelease(), movie.getPrice(), movie.getLength(), bookingDto);
        return bookDto;
    }

    public static MusicAlbumDto convertToDto(MusicAlbum album, Booking booking){

        BookingDto bookingDto = convertToDto(booking, booking.getUser(), booking.getBookingType());
        MusicAlbumDto musicAlbumDto = new MusicAlbumDto(album.getId(), album.getBarcode(), album.getTitle(), album.getAuthor(),
                album.getDateOfRelease(), album.getPrice(), album.getNumberOfSongs(), album.getTotalLength(), bookingDto);
        return musicAlbumDto;
    }

    public static NewspaperDto convertToDto(Newspaper newspaper){

        NewspaperDto newspaperDto = new NewspaperDto(newspaper.getId(), newspaper.getTitle(), newspaper.getDate(),
                newspaper.getNumberOfPages());
        return newspaperDto;
    }

    public static ArchiveDto convertToDto(Archive archive){

        ArchiveDto archiveDto = new ArchiveDto(archive.getId(), archive.getTitle(), archive.getDate(),
                archive.getNumberOfPages());
        return archiveDto;
    }
}

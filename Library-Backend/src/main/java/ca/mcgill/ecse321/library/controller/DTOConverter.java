package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.dto.ArchiveDto;
import ca.mcgill.ecse321.library.dto.BookDto;
import ca.mcgill.ecse321.library.dto.BookingDto;
import ca.mcgill.ecse321.library.dto.BookingTypeDto;
import ca.mcgill.ecse321.library.dto.LendingDto;
import ca.mcgill.ecse321.library.dto.LibrarianDto;
import ca.mcgill.ecse321.library.dto.MemberDto;
import ca.mcgill.ecse321.library.dto.MovieDto;
import ca.mcgill.ecse321.library.dto.MusicAlbumDto;
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.dto.ReservationDto;
import ca.mcgill.ecse321.library.dto.UserDto;
import ca.mcgill.ecse321.library.dto.WorkDayDto;
import ca.mcgill.ecse321.library.model.Archive;
import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Booking;
import ca.mcgill.ecse321.library.model.BookingType;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.Movie;
import ca.mcgill.ecse321.library.model.MusicAlbum;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.model.Reservation;
import ca.mcgill.ecse321.library.model.User;
import ca.mcgill.ecse321.library.model.WorkDay;

public class DTOConverter {
    public static BookingTypeDto convertToDto(BookingType bt) {
        //need to differentiate between lending and reservation
        if (bt instanceof Lending) {
            return new LendingDto(bt.getId(), ((Lending) bt).getReturnDate()); //return LendingDto
        } else {
            return new ReservationDto(bt.getId(), ((Reservation) bt).getExpirationDate()); //return ReservationDto
        }
    }

    public static UserDto convertToDto(User user) {
        //not taking into consideration different types of users
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getAddress());
    }

    /**
     * @param member
     * @return
     * @author Abd-El-Aziz Zayed
     */
    public static MemberDto convertToDto(Member member) {
        return new MemberDto(member.getId(), member.getUsername(), member.getPassword(), member.getAddress(),
                member.getMemberType(), member.getMemberStatus(), member.getMonthlyFee(), member.getStartDate());
    }

    /**
     * @param librarian
     * @return
     * @author Abd-El-Aziz Zayed
     */
    public static LibrarianDto convertToDto(Librarian librarian) {
        return new LibrarianDto(librarian.getId(), librarian.getUsername(), librarian.getPassword(), librarian.getAddress());
    }

    public static BookingDto convertToDto(Booking b, User aUser, BookingType bt) {
        BookingTypeDto btDto = bt == null ? null : convertToDto(bt);
        UserDto userDto = aUser == null ? null : convertToDto(aUser);

        return new BookingDto(b.getId(), b.getBookingDate(), userDto, btDto);
    }

    public static BookDto convertToDto(Book book, Booking booking) {

        BookingDto bookingDto = booking==null? null : convertToDto(booking, booking.getUser(), booking.getBookingType());
        BookDto bookDto = new BookDto(book.getId(), book.getBarcode(), book.getTitle(), book.getAuthor(),
                book.getDateOfRelease(), book.getPrice(), book.getIsbn(), book.getNumberOfPages(), bookingDto);
        return bookDto;
    }

    public static MovieDto convertToDto(Movie movie, Booking booking) {

        BookingDto bookingDto = booking == null ? null :  convertToDto(booking, booking.getUser(), booking.getBookingType());
        MovieDto bookDto = new MovieDto(movie.getId(), movie.getBarcode(), movie.getTitle(), movie.getAuthor(),
                movie.getDateOfRelease(), movie.getPrice(), movie.getLength(), bookingDto);
        return bookDto;
    }

    public static MusicAlbumDto convertToDto(MusicAlbum album, Booking booking) {

        BookingDto bookingDto = booking == null ? null : convertToDto(booking, booking.getUser(), booking.getBookingType());
        MusicAlbumDto musicAlbumDto = new MusicAlbumDto(album.getId(), album.getBarcode(), album.getTitle(), album.getAuthor(),
                album.getDateOfRelease(), album.getPrice(), album.getNumberOfSongs(), album.getTotalLength(), bookingDto);
        return musicAlbumDto;
    }

    public static NewspaperDto convertToDto(Newspaper newspaper) {

        NewspaperDto newspaperDto = new NewspaperDto(newspaper.getId(), newspaper.getTitle(), newspaper.getDate(),
                newspaper.getNumberOfPages());
        return newspaperDto;
    }

    public static ArchiveDto convertToDto(Archive archive) {

        ArchiveDto archiveDto = new ArchiveDto(archive.getId(), archive.getTitle(), archive.getDate(),
                archive.getNumberOfPages());
        return archiveDto;
    }

    public static WorkDayDto convertToDto(WorkDay aworkday) {
        WorkDayDto workdayDto = new WorkDayDto(aworkday.getId(), aworkday.getDayOfWeek(), aworkday.getStartTime(), aworkday.getEndTime());
        return workdayDto;
    }
}

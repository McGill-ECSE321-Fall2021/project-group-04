package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.model.Booking;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);

    boolean existsBookById(Long id);

    List<Book> findByAuthor(String author);

    Book findBookByTitle(String title);

    Book findBookByIsbn(String isbn);

    Book findBookByBarcode(String barcode);

    List<Book> findByBooking(Booking booking);
}

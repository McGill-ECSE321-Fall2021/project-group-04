package ca.mcgill.ecse321.library.controller;

import ca.mcgill.ecse321.library.model.Book;
import ca.mcgill.ecse321.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
public class BookController {

    @Autowired
    BookService bookService;

//    @PostMapping(value = {"/create_book"})
//    public ResponseEntity<?> createBook(@RequestParam String barCode,@RequestParam String title,@RequestParam String author,
//                                        @RequestParam String dateOfRelease,@RequestParam String price,@RequestParam String isbn,@RequestParam String numberOfPages){
//        Book book = null;
//        try{
//            book = bookService.createBook(barCode, title, author, dateOfRelease, price, isbn, numberOfPages);
//        }
//        catch(IllegalArgumentException e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>()
//    }
}

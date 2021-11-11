package ca.mcgill.ecse321.library;

import ca.mcgill.ecse321.library.service.BookingService;
import ca.mcgill.ecse321.library.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @RequestMapping("/")
    public String greeting() {
        return "Hello ECSE321!";
    }

}
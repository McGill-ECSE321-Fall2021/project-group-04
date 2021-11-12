package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookRepository;
import ca.mcgill.ecse321.library.dao.BookingRepository;
import ca.mcgill.ecse321.library.dao.LendingRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.dao.MovieRepository;
import ca.mcgill.ecse321.library.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.library.model.Lending;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    LendingRepository lendingRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MusicAlbumRepository musicAlbumRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public List<Lending> getAllLendings() {
        return toList(lendingRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}

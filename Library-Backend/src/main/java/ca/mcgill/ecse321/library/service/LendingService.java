package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.*;
import ca.mcgill.ecse321.library.model.Lending;
import ca.mcgill.ecse321.library.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    public List<Lending> getAllLendings(){
        return toList(lendingRepository.findAll());
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}

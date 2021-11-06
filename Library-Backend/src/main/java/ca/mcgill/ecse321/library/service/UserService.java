package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    // TODO
    @Transactional
    public User login(String username, String password) {
        return null;
    }

    // TODO
    public static boolean usernameValid(String username) {
        return true;
    }

    // TODO
    public static boolean passwordValid(String password) {
        return true;
    }

    // TODO
    public static boolean passwordAddress(String address) {
        return true;
    }

    public static <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;

    }
}

package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
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
        if(!memberRepository.existsMemberByUsername(username) &&
                !librarianRepository.existsLibrarianByUsername(username) &&
                !headLibrarianRepository.existsHeadLibrarianByUsername(username)) {
            throw new IllegalArgumentException("Invalid Username.");
        }

        Member member = memberRepository.findMemberByUsername(username);
        if(member != null && member.getPassword().equals(password)) {
            return member;
        }

        Librarian librarian = librarianRepository.findLibrarianByUsername(username);
        if(librarian != null && librarian.getPassword().equals(password)) {
            return librarian;
        }

        HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByUsername(username);
        if(headLibrarian != null && headLibrarian.getPassword().equals(password)) {
            return headLibrarian;
        }

        throw new IllegalArgumentException("Incorrect Password.");
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
    public static boolean addressValid(String address) {
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

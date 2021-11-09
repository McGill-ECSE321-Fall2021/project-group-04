package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.library.dao.LibrarianRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.HeadLibrarian;
import ca.mcgill.ecse321.library.model.Librarian;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.User;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private static MemberRepository memberRepository;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private HeadLibrarianRepository headLibrarianRepository;

    public static boolean checkValidUsername(String username) {
        if (username == null || username == "") {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (memberRepository.findMemberByUsername(username) == null) {
            return true;
        }
        throw new IllegalArgumentException("Username already exists.");
    }


    /**
     * @param password
     * @return
     */
    public static boolean checkValidPassword(String password) {
        if (password == null || password == "") {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("The password length cannot be less than 8 characters.");
        }
        if (password.length() < 20) {
            throw new IllegalArgumentException("The password length cannot be more than 20 characters.");
        }

        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                upperCaseFlag = true;
            } else if (Character.isLowerCase(password.charAt(i))) {
                lowerCaseFlag = true;
            } else if (Character.isDigit(password.charAt(i))) {
                numberFlag = true;
            }
        }

        if (!upperCaseFlag) {
            throw new IllegalArgumentException("The password must contain at least one uppercase character.");
        }
        if (!lowerCaseFlag) {
            throw new IllegalArgumentException("The password must contain at least one lowercase character.");
        }
        if (!numberFlag) {
            throw new IllegalArgumentException("The password must contain at least one numeric character.");
        }

        return true;
    }

    // Need to Review

    /**
     * @param address
     * @return
     */
    public static boolean checkValidAddress(String address) {
        if (address == null || address.equals("")) {
            throw new IllegalArgumentException("The address cannot be empty.");
        }

        return true;
    }

    @Transactional
    public User login(String username, String password) {
        if (!memberRepository.existsMemberByUsername(username) &&
                !librarianRepository.existsLibrarianByUsername(username) &&
                !headLibrarianRepository.existsHeadLibrarianByUsername(username)) {
            throw new IllegalArgumentException("Invalid Username.");
        }

        Member member = memberRepository.findMemberByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        }

        Librarian librarian = librarianRepository.findLibrarianByUsername(username);
        if (librarian != null && librarian.getPassword().equals(password)) {
            return librarian;
        }

        HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByUsername(username);
        if (headLibrarian != null && headLibrarian.getPassword().equals(password)) {
            return headLibrarian;
        }

        throw new IllegalArgumentException("Incorrect Password.");
    }
}
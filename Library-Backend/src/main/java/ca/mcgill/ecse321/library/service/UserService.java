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

public class UserService {

    /**
     * @param password
     * @return
     * @author Jewoo Lee
     */
    public static boolean checkValidPassword(String password) {
        if (password == null || password == "") {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("The password length cannot be less than 8 characters.");
        }
        if (password.length() > 20) {
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
     * @author Jewoo Lee
     */
    public static boolean checkValidAddress(String address) {
        if (address == null || address.equals("")) {
            throw new IllegalArgumentException("The address cannot be empty.");
        }

        return true;
    }
}
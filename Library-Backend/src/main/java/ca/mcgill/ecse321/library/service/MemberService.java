package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.BookingRepository;
import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.*;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BookingRepository bookingRepository;

    /**
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @param aMemberType
     * @param aMemberStatus
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public Member createMember(String aUsername, String aPassword, String aAddress, Member.MemberType aMemberType, Member.MemberStatus aMemberStatus) {
        checkValidUsername(aUsername);
        UserService.checkValidPassword(aPassword);
        UserService.checkValidAddress(aAddress);

        Member member = new Member();
        member.setUsername(aUsername);
        member.setPassword(aPassword);
        member.setAddress(aAddress);
        member.setMemberType(aMemberType);
        member.setMemberStatus(aMemberStatus);

        memberRepository.save(member);

        return member;
    }

    /**
     * @param username
     * @param newPassword
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public Member changeMemberPassword(String username, String newPassword) {
        Member member = memberRepository.findMemberByUsername(username);

        if (member == null) {
            throw new IllegalArgumentException("Member is not found.");
        }

        if (UserService.checkValidPassword(newPassword)) {
            member.setPassword(newPassword);
        }
        memberRepository.save(member);
        return member;
    }

    /**
     * @param username
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public boolean deleteMember(String username) {
        Member member = getMember(username);
        if (member == null) {
            throw new IllegalArgumentException("Member is not found.");
        }
        memberRepository.delete(member);
        return true;
    }

    /**
     * @param username
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public Member getMember(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    /**
     * @param username
     * @return
     * @author alymo
     */
    @Transactional
    public List<Booking> getMemberBookings(String username) {
        return bookingRepository.findByUser(memberRepository.findMemberByUsername(username));
    }

    /**
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public List<Member> getAllMembers() {
        return Services.toList(memberRepository.findAll());
    }

    /**
     * @param username
     * @return
     * @author Jewoo Lee
     */
    public boolean checkValidUsername(String username) {
        if (username == null || username == "") {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (memberRepository.findMemberByUsername(username) == null) {
            return true;
        }
        throw new IllegalArgumentException("Username already exists.");
    }

    /**
     * @param username
     * @param password
     * @return
     * @author Jewoo Lee
     */
    @Transactional
    public Member login(String username, String password) {
        if (memberRepository.findMemberByUsername(username) == null) {
            throw new IllegalArgumentException("Invalid Username.");
        }

        Member member = memberRepository.findMemberByUsername(username);
        if (member != null && member.getPassword().equals(password)) {
            return member;
        }

        throw new IllegalArgumentException("Incorrect Password.");
    }
}

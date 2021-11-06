package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Member;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Member createMember(String aUsername, String aPassword, String aAddress, Member.MemberType aMemberType, Member.MemberStatus aMemberStatus) {
        if(aUsername == null || aUsername == "") {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if(aPassword == null || aPassword == "") {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if(aAddress == null || aAddress == "") {
            throw new IllegalArgumentException("Address cannot be empty.");
        }

        UserService.usernameValid(aUsername);
        UserService.passwordValid(aPassword);
        UserService.addressValid(aAddress);

        Member member = new Member();
        member.setUsername(aUsername);
        member.setPassword(aPassword);
        member.setAddress(aAddress);
        member.setMemberType(aMemberType);
        member.setMemberStatus(aMemberStatus);

        memberRepository.save(member);

        return member;
    }

    // TODO
    @Transactional
    public Member editMemberPassword(String username, String newPassword) {
       Member member = memberRepository.findMemberByUsername(username);

       if(username == "") {
           throw new IllegalArgumentException("Username cannot be empty.");
       }
       if(member == null) {
           throw new IllegalArgumentException("Member is not found.");
       }
       if(newPassword == null || newPassword == "") {
           throw new IllegalArgumentException("The new password cannot be empty");
       }

       if(UserService.passwordValid(newPassword)) {
           member.setPassword(newPassword);
       }
       memberRepository.save(member);
       return member;
    }

    // TODO
    @Transactional
    public boolean deleteMember(String username) {
        Member member = getMember(username);
        if(member == null) {
            throw new IllegalArgumentException("Member is not found.");
        }
        memberRepository.delete(member);
        return true;
    }

    // TODO
    @Transactional
    public Member getMember(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    // TODO
    @Transactional
    public List<Member> getAllMembers() {
        return UserService.toList(memberRepository.findAll());
    }



}

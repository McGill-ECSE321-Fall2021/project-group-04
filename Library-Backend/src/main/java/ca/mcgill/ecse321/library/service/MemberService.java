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

    /**
     *
     * @param aUsername
     * @param aPassword
     * @param aAddress
     * @param aMemberType
     * @param aMemberStatus
     * @return
     */
    @Transactional
    public Member createMember(String aUsername, String aPassword, String aAddress, Member.MemberType aMemberType, Member.MemberStatus aMemberStatus) {
        UserService.checkValidUsername(aUsername);
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
     *
     * @param username
     * @param newPassword
     * @return
     */
    @Transactional
    public Member changeMemberPassword(String username, String newPassword) {
        UserService.checkValidUsername(username);
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
     *
     * @param username
     * @return
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
     *
     * @param username
     * @return
     */
    @Transactional
    public Member getMember(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    /**
     *
     * @return
     */
    @Transactional
    public List<Member> getAllMembers() {
        return Services.toList(memberRepository.findAll());
    }


}

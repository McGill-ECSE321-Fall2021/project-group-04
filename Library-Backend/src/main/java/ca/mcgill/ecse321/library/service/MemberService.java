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
    private MemberRepository memberRepository;

    // TODO
    @Transactional
    public Member createMember(String aUsername, String aPassword, String aAddress, Member.MemberType aMemberType, Member.MemberStatus aMemberStatus) {
        return null;
    }

    // TODO
    @Transactional
    public boolean deleteMember(String username) {
        return false;
    }

    // TODO
    @Transactional
    public Member getMember(String username) {
        return null;
    }

    // TODO
    @Transactional
    public List<Member> getAllMembers() {
        return null;
    }

}

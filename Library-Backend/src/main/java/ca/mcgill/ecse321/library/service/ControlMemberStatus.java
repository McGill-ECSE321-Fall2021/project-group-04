package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.model.Member;

public class ControlMemberStatus {

    public boolean verifyMemberType(Member member) {
        if(member.getMemberType() == Member.MemberType.Foreign) {
            return false;
        }
        return true;
    }

    public boolean verifyMemberStatus(Member member) {
        if(member.getMemberStatus() == Member.MemberStatus.Blacklisted) {
            return false;
        }
        return true;
    }

    public boolean verifyMemberAddress(Member member) {
        if(member.getAddress() == null || member.getAddress().equals("")) {
            return false;
        }
        return true;
    }

    public boolean verifyMemberID(Member member) {
        if(member.getId() == null || member.getId().equals("")) {
            return false;
        }
        return true;
    }
}

package ca.mcgill.ecse321.library.dto;

import ca.mcgill.ecse321.library.model.Member;
import java.sql.Date;

public class MemberDto extends UserDto {

    private final Member.MemberType memberType;
    private final Member.MemberStatus memberStatus;
    private final int monthlyFee;
    private final Date startDate;

    public MemberDto(Long aId, String aUsername, String aPassword, String aAddress, Member.MemberType aMemberType, Member.MemberStatus aMemberStatus, int aMonthlyFee, Date aStartDate) {
        super(aId, aUsername, aPassword, aAddress);
        memberType = aMemberType;
        memberStatus = aMemberStatus;
        monthlyFee = aMonthlyFee;
        startDate = aStartDate;
    }

    public Member.MemberType getMemberType() {
        return memberType;
    }

    public Member.MemberStatus getMemberStatus() {
        return memberStatus;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public Date getStartDate() {
        return startDate;
    }
}

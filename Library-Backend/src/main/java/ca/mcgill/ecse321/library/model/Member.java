package ca.mcgill.ecse321.library.model;

import java.sql.Date;
import javax.persistence.Entity;

@Entity
public class Member extends User {

    public enum MemberStatus {
        Active, Blacklisted
    }

    public enum MemberType {
        Local, Foreign
    }

    private MemberType memberType;
    private MemberStatus memberStatus;
    private int monthlyFee;
    private Date startDate;

    public Member(String aUsername, String aPassword, String aAddress, MemberType aMemberType, MemberStatus aMemberStatus, int aMonthlyFee, Date aStartDate) {
        super(aUsername, aPassword, aAddress);
        memberType = aMemberType;
        memberStatus = aMemberStatus;
        monthlyFee = aMonthlyFee;
        startDate = aStartDate;
    }

    public Member() {
        super();
    }

    public MemberType getMemberType() {
        return this.memberType;
    }

    public void setMemberType(MemberType value) {
        this.memberType = value;
    }

    public MemberStatus getMemberStatus() {
        return this.memberStatus;
    }

    public void setMemberStatus(MemberStatus value) {
        this.memberStatus = value;
    }

    public int getMonthlyFee() {
        return this.monthlyFee;
    }

    public void setMonthlyFee(int value) {
        this.monthlyFee = value;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date value) {
        this.startDate = value;
    }

}

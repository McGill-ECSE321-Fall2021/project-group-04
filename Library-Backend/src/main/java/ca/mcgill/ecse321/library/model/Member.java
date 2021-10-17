import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Member extends User{
private MemberType memberType;

public Member(String aId, String aUsername, String aPassword, String aAddress, MemberType aMemberType, MemberStatus aMemberStatus, int aMonthlyFee, Date aStartDate)
{
  super(aId, aUsername, aPassword, aAddress);
  memberType = aMemberType;
  memberStatus = aMemberStatus;
  monthlyFee = aMonthlyFee;
  startDate = aStartDate;
}
   
   public void setMemberType(MemberType value) {
this.memberType = value;
    }
public MemberType getMemberType() {
return this.memberType;
    }
private MemberStatus memberStatus;

public void setMemberStatus(MemberStatus value) {
this.memberStatus = value;
    }
public MemberStatus getMemberStatus() {
return this.memberStatus;
    }
private int monthlyFee;

public void setMonthlyFee(int value) {
this.monthlyFee = value;
    }
public int getMonthlyFree() {
return this.monthlyFee;
    }
private Date startDate;

public void setStartDate(Date value) {
this.startDate = value;
    }
public Date getStartDate() {
return this.startDate;
       }
   }

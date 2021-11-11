package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Member;
import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestMemberService {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private static MemberService memberService;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "Password1234";
    private static final String ADDRESS = "1234 University, Montreal, Quebec";

    private static final Member.MemberType MEMBER_TYPE = Member.MemberType.Local;
    private static final Member.MemberStatus MEMBER_STATUS = Member.MemberStatus.Active;

    private static final int MONTHLY_FEE = 0;
    private static final Date START_DATE = Date.valueOf("2020-05-29");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(memberRepository.findMemberByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                Member member = new Member();

                member.setUsername(USERNAME);
                member.setPassword(PASSWORD);
                member.setAddress(ADDRESS);
                member.setMemberType(MEMBER_TYPE);
                member.setMemberStatus(MEMBER_STATUS);
                member.setMonthlyFee(MONTHLY_FEE);
                member.setStartDate(START_DATE);

                return member;
            }

            return null;
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testMemberSignUp() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            fail();
        }

        assertNotNull(member);
        assertEquals(username, member.getUsername());
        assertEquals(password, member.getPassword());
        assertEquals(address, member.getAddress());
        assertEquals(memberType, member.getMemberType());
        assertEquals(memberStatus, member.getMemberStatus());

    }

    @Test
    public void testMemberSignUpNull() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "The username cannot be empty.");

    }

    @Test
    public void testMemberSignUpExistingUsername() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "username";
        String password = "TestPassword1234";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "This username already exists.");
    }

    @Test
    public void testUpdateMemberPassword() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "Newpassword123");
        }
        catch(IllegalArgumentException e) {
            fail();
        }
        assertNotNull(member);
        assertEquals("Newpassword123", member.getPassword());
        assertEquals(USERNAME, member.getUsername());
    }

    @Test
    public void testUpdateMemberPasswordInvalidUpperCase() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "invalidpassword123");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. Password must contain at least one uppercase character.");
    }

    @Test
    public void testUpdateMemberPasswordInvalidLowerCase() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "INVALIDPASSWORD123");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. Password must contain at least one lowercase character.");
    }

    @Test
    public void testUpdateMemberPasswordForEmptyPassword() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, null);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. New password cannot be empty.");
    }

    @Test
    public void testUpdateMemberPasswordForNoNumber() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "InvalidPassword");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. New password must contain at least one number.");
    }

    @Test
    public void testUpdateMemberPasswordForShorterThan8() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "InvPwd1");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. New password must be longer than 8 characters.");
    }

    @Test
    public void testUpdateMemberPasswordForLongerthan20() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(USERNAME, "InvalidPasswordThatIsLongerThan20");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. New password must be shorter than 20 characters.");
    }

    @Test
    public void testDeleteMember() {
        boolean deleted = false;
        try {
            deleted = memberService.deleteMember(USERNAME);
        }
        catch(IllegalArgumentException e) {
            fail();
        }
        assertTrue(deleted);
    }

    @Test
    public void testDeleteMemberUnknown() {
        String error = null;
        try {
            memberService.deleteMember("Non-existing Username");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(error, "Member is not found.");
    }

    @Test
    public void testInvalidMemberPasswordForUpperCase() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "testpassword1234";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(member);
        assertEquals(error, "Password must contain at least one uppercase character.");
    }

    @Test
    public void testInvalidMemberPasswordForLowerCase() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "TESTPASSWORD1234";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(member);
        assertEquals(error, "Password must contain at least one lowercase character.");
    }

    @Test
    public void testInvalidMemberPasswordForNoNumber() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "TestPassword";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(member);
        assertEquals(error, "Password must contain at least one number.");
    }

    @Test
    public void testInvalidMemberPasswordForShorterThan8() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "TstPwd1";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(member);
        assertEquals(error, "Password must be at least 8 characters.");
    }

    @Test
    public void testInvalidMemberPasswordForLongerThan20() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        String username = "TestUsername";
        String password = "TestPasswordForLongerThan20";
        String address = "1234 Test, Address, Province";
        Member.MemberType memberType = Member.MemberType.Local;
        Member.MemberStatus memberStatus = Member.MemberStatus.Active;
        String error = "";

        try {
            member = memberService.createMember(username, password, address, memberType, memberStatus);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(member);
        assertEquals(error, "Password must be shorter than 20 characters.");
    }
}

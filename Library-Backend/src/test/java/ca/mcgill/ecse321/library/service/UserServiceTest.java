package ca.mcgill.ecse321.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Member;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


/**
 * @author JewooLee
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private static final String MEMBER_USERNAME = "TestMember";
    private static final String MEMBER_PASSWORD = "TestPassword123";

    private static final String MEMBER_FIRSTNAME = "Andy";
    private static final String MEMBER_LASTNAME = "Bob";
    private static final String MEMBER_ADDRESS = "0000 AAA BBB CCC";
    private static final String MEMBER_TYPE = "Local";
    private static final String MEMBER_STATUS = "Active";

    @BeforeEach
    public void setMockOutput() {

    }

    @Test
    public void testSignupMember() {

    }

    @Test
    public void testSignupMemberExistingUsername() {

    }

    @Test
    public void testUpdateMemberPassword() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;

        try {
            member = memberService.changeMemberPassword(MEMBER_USERNAME, "Newpassword123");
        }
        catch(IllegalArgumentException e) {
            fail();
        }
        assertNotNull(member);
        assertEquals("Newpassword123", member.getPassword());
        assertEquals(MEMBER_USERNAME, member.getUsername());
    }

    @Test
    public void testUpdateMemberPasswordInvalidUpperCase() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(MEMBER_USERNAME, "invalidpassword123");
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. Password must contain at least one uppercase character.");
    }

    @Test
    public void testUpdateMemberPasswordEmptyPassword() {
        assertEquals(0, memberService.getAllMembers().size());
        Member member = null;
        String error = null;

        try {
            member = memberService.changeMemberPassword(MEMBER_USERNAME, null);
        }
        catch(IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(member);
        assertEquals(error, "Invalid Password. New password cannot be empty.");
    }

    @Test
    public void testDeleteMember() {
        boolean deleted = false;
        try {
            deleted = memberService.deleteMember(MEMBER_USERNAME);
        }
        catch(IllegalArgumentException e) {
            fail();
        }
        assertTrue(deleted);
    }

    @Test
    public void testDeleteMemberNotFound() {
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
    public void testInvalidPasswordUpperCase() {

    }

    @Test
    public void testInvalidPasswordLowerCase() {

    }

    @Test
    public void testInvalidPasswordNumber() {

    }

    @Test
    public void testInvalidPasswordLengthUnder8() {

    }

    @Test
    public void testInvalidPasswordLengthOver20() {

    }
}

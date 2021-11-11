package ca.mcgill.ecse321.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.User;
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
    private UserService userService;

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

}

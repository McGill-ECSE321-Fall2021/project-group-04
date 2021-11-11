package ca.mcgill.ecse321.library.service;

import ca.mcgill.ecse321.library.dao.MemberRepository;
import ca.mcgill.ecse321.library.model.Member;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestMemberService {

    @Mock
    private MemberRepository memberRepository;

    private static final String USERNAME = "aziz";
    private static final String PASSWORD = "aziz123";
    private static final String ADDRESS = "1234 University, Montreal, QC";

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
    public void testCreateMember() {}

    @Test
    public void testCreateMemberNull() {}

    @Test
    public void testCreateMemberEmpty() {}

    @Test
    public void testCreateMemberNotUnique() {}

    @Test
    public void testChangeMemberPassword() {}

    @Test
    public void testChangeMemberPasswordIfNull() {}

    @Test
    public void testChangeMemberPasswordIfInvalid() {}

    @Test
    public void testDeleteMember() {}

    @Test
    public void testDeleteUnknownMember() {}
}

package ca.mcgill.ecse321.library.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.library.model.Member;
import ca.mcgill.ecse321.library.model.Member.MemberStatus;
import ca.mcgill.ecse321.library.model.Member.MemberType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMemberPersistence {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void clearDatabase() {
        memberRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCustomer() {
    Date startDate = Date.valueOf("2021-12-23");
    	
    	Member testMember = new Member();
    	testMember.setUsername("happy");
    	testMember.setPassword("1234");
    	testMember.setAddress("199 Rotond");
    	testMember.setMemberType(MemberType.Local);
    	testMember.setMemberStatus(MemberStatus.Active);
    	testMember.setMonthlyFee(0);
    	testMember.setStartDate(startDate);

		memberRepository.save(testMember);
		
		testMember = null;

		testMember = memberRepository.findMemberByUsername("happy");
		assertNotNull(testMember);
		
		assertEquals("happy", testMember.getUsername());
		assertEquals("1234",testMember.getPassword());
		assertEquals("199 Rotond",testMember.getAddress());
		assertEquals(MemberType.Local,testMember.getMemberType());
		assertEquals(MemberStatus.Active,testMember.getMemberStatus());
		assertEquals(0,testMember.getMonthlyFee());
		assertEquals(startDate,testMember.getStartDate());
		
		assertEquals(true,memberRepository.existsMemberByUsername(testMember.getUsername()));
		
    }
}

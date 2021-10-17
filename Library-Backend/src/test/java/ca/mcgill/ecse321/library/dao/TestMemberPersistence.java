package ca.mcgill.ecse321.library.dao;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    }
}

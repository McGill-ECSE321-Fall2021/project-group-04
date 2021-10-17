package ca.mcgill.ecse321.library.dao;

import ca.mcgill.ecse321.library.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
    Member findMemberByUsername(String username);

    boolean existsMemberByUsername(String username);
}

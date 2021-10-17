package ca.mcgill.ecse321.library.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.library.model.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	Member findMemberByUsername(String username);
	boolean existsMemberByUsername(String username);
}

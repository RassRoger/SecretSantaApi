package thomson.reuters.SecretSantaApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import thomson.reuters.SecretSantaApi.models.Member;

@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {
	
	@Query("select m from Member m where m.groupId=?1")
	List<Member> findGroupId(Long groupId);
}

package thomson.reuters.SecretSantaApi.services;

import java.util.List;

import thomson.reuters.SecretSantaApi.models.Member;
import thomson.reuters.SecretSantaApi.models.MemberDto;

public interface MemberService {
	Member findById();
	List<Member> findByGroupId(Long groupId);
	List<Member> peopleExchange(Long groupId);
	Member save(MemberDto dto);
	List<Member> saveAll(List<MemberDto> lstMemberDto);
	Member update(Member member);
}

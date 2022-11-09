package thomson.reuters.SecretSantaApi.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import thomson.reuters.SecretSantaApi.models.Group;
import thomson.reuters.SecretSantaApi.models.Member;
import thomson.reuters.SecretSantaApi.models.MemberDto;
import thomson.reuters.SecretSantaApi.repositories.GroupRepository;
import thomson.reuters.SecretSantaApi.repositories.MembersRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private MembersRepository memberRepo;
	private GroupRepository groupRepo;

	@Autowired
	public MemberServiceImpl(MembersRepository memberRepo, GroupRepository groupRepo) {
		this.memberRepo = memberRepo;
		this.groupRepo = groupRepo;
	}

	@Override
	public Member findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Member> findByGroupId(Long groupId) {
		List<Member> members = memberRepo.findGroupId(groupId);
		return members;
	}

	@Override
	public List<Member> peopleExchange(Long groupId) {
		List<Member> elements = memberRepo.findGroupId(groupId);
		List<Member> membersRand = elements;
		List<String> blacklist = new ArrayList<String>();
		for (int i = 0; i < membersRand.size(); i++) {
			String[] blackListMember = membersRand.get(i).getBlacklist().split("\\s*,\\s*");
			List<String> black = addBlacklist(blackListMember, blacklist);
			List<Member> memFind = elements.stream().filter(s -> !black.contains(s.getName()))
					.collect(Collectors.toList());
			if (memFind.size() == 0) {
				elements = permutation(elements, membersRand.get(i));
				break;
			}
			Member memAssign = createRandom(memFind);
			Member mem = elements.get(i);
			mem.setPersonExchange(memAssign.getName());
			blacklist.add(memAssign.getName());
		}
		for (Member mem : elements) {
			memberRepo.save(mem);
		}
		elements = memberRepo.findGroupId(groupId);
		return elements;
	}

	@Override
	@Transactional
	public Member save(MemberDto dto) {
		Long groupId = dto.getGroupId();
		if (groupId == null) {
			Group group = groupRepo.save(new Group(null, dto.getGroupName()));
			groupId = group.getId();
		}
		Member member = memberRepo
				.save(new Member(null, groupId, dto.getName(), dto.getEmail(), dto.isAdmin(), dto.getBlacklist()));
		System.out.println(member);
		return member;
	}
	
	public Member update(Member member) {
		return memberRepo.save(member);
	}
	
	private List<String> addBlacklist(String[] blackListMember, List<String> blacklist) {
		List<String> black = new ArrayList<String>();
		for (String blackMem : blackListMember) {
			black.add(blackMem);
		}
		for (String blackT : blacklist) {
			black.add(blackT);
		}
		return black;
	}

	private Member createRandom(List<Member> members) {
		Member member;
		double randomIndex = Math.floor(Math.random() * members.size());
		member = members.get((int) randomIndex);
		return member;
	}

	private List<Member> permutation(List<Member> members, Member member) {
		String personExchange = "";
		List<String> blackListMember = member.getBlacklist() == null ? new ArrayList<>()
				: Arrays.asList(member.getBlacklist().split("\\s*,\\s*"));
		for (int i = 0; i < members.size(); i++) {
			List<String> blackListCurrent = members.get(i).getBlacklist() == null ? new ArrayList<>()
					: Arrays.asList(members.get(i).getBlacklist().split("\\s*,\\s*"));
			if (!blackListCurrent.contains(member.getName())) {
				if (!blackListMember.contains(members.get(i).getName())) {
					personExchange = members.get(i).getName();
					members.get(i).setPersonExchange(member.getName());
					break;
				}
			}
		}
		Member mem = members.stream().filter(s -> s.getName() == member.getName()).collect(Collectors.toList()).get(0);
		mem.setPersonExchange(personExchange);
		return members;
	}

	@Override
	public List<Member> saveAll(List<MemberDto> lstMemberDto) {
		Long groupId = 0L;
		for (int i = 0; i < lstMemberDto.size(); i++) {
			MemberDto dto = lstMemberDto.get(i);
			if (i == 0) {
				Group group = groupRepo.save(new Group(null, dto.getGroupName()));
				groupId = group.getId();
			}
			Member member = memberRepo
					.save(new Member(null, groupId, dto.getName(), dto.getEmail(), dto.isAdmin(), dto.getBlacklist()));
			System.out.println(member);
		}
		List<Member> elements = memberRepo.findGroupId(groupId);
		return elements;
	}

}

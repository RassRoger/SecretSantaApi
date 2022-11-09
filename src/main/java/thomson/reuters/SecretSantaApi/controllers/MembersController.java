package thomson.reuters.SecretSantaApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thomson.reuters.SecretSantaApi.models.Member;
import thomson.reuters.SecretSantaApi.models.MemberDto;
import thomson.reuters.SecretSantaApi.services.MemberService;

@RestController
@RequestMapping("/api")
public class MembersController {
	
	private MemberService memberService;
	
	@Autowired
	public MembersController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/{groupId}")
	public ResponseEntity<?> getMembers(@PathVariable Long groupId){
		List<Member> members = memberService.findByGroupId(groupId);
		return ResponseEntity.ok(members);
	}
	
	@PostMapping("/member")
	public ResponseEntity<?> saveMember(@RequestBody MemberDto dto){
		Member member = memberService.save(dto);
		return ResponseEntity.ok(member);
	}
	
	@PutMapping("/member")
	public ResponseEntity<?> updateMember(@RequestBody Member member){
		Member memberUpdt = memberService.update(member);
		return ResponseEntity.ok(memberUpdt);
	}
	
	@PostMapping("/members")
	public ResponseEntity<?> saveMembers(@RequestBody List<MemberDto> arrDtos){
		List<Member> members = memberService.saveAll(arrDtos);
		return ResponseEntity.ok(members);
	}
	
	@PutMapping("/members/{groupId}")
	public ResponseEntity<?> saveMember(@PathVariable Long groupId){
		List<Member> members = memberService.peopleExchange(groupId);
		return ResponseEntity.ok(members);
	}

}

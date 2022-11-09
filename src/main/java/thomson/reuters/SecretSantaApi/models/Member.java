package thomson.reuters.SecretSantaApi.models;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long groupId;
	private String name;
	private String email;
	private boolean isAdmin;
	private String personExchange;
	private String blacklist;
	
	public Member() {
	}
	
	public Member(Long id, Long groupId, String name, String email, boolean isAdmin, String blacklist) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.name = name;
		this.email = email;
		this.isAdmin = isAdmin;
		this.blacklist = blacklist;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersonExchange() {
		return personExchange;
	}

	public void setPersonExchange(String personExchange) {
		this.personExchange = personExchange;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", groupId=" + groupId + ", name=" + name + ", email=" + email + ", isAdmin="
				+ isAdmin;
	}


}

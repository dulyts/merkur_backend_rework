package hu.asd.merkur.auth;

import java.util.UUID;

import hu.asd.merkur.common.persist.entity.User;
import lombok.Data;

@Data
public class MerkurAuthUser {

	private UUID id;
	private String name;
	private String neptun;
	private String email;
	private String gender;

	public MerkurAuthUser(UUID id, String name, String neptun, String email, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.neptun = neptun;
		this.email = email;
		this.gender = gender;
	}

	public MerkurAuthUser(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.neptun = user.getNeptun();
		this.email = user.getEmail();
		this.gender = user.getGender().getCode();
	}

}

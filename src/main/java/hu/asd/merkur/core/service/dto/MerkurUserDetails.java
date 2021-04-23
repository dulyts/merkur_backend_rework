package hu.asd.merkur.core.service.dto;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import hu.asd.merkur.auth.MerkurAuthUser;
import lombok.Getter;

@Getter
public class MerkurUserDetails extends org.springframework.security.core.userdetails.User {

	private UUID id;

	private MerkurAuthUser user;

	public MerkurUserDetails(MerkurAuthUser user, GrantedAuthority currentRole) {
		super(user.getEmail(), "", Arrays.asList(currentRole));
		this.id = user.getId();
		this.user = user;
	}
}

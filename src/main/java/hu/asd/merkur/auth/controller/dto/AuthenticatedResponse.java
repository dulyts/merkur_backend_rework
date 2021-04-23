package hu.asd.merkur.auth.controller.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import hu.asd.merkur.auth.MerkurAuthUser;
import hu.asd.merkur.core.service.dto.MerkurUserDetails;
import lombok.Getter;

@Getter
public class AuthenticatedResponse {

	private String userId;
	private String name;
	private List<String> roles;
	private boolean authenticated;
	private String neptun;
	private String gender;

	public AuthenticatedResponse(MerkurUserDetails user) {
		authenticated = user != null;
		if (authenticated) {
//			User loggedInUser = user.getUser();
			MerkurAuthUser loggedInUser = user.getUser();
			Collection<GrantedAuthority> roles = user.getAuthorities();
			this.neptun = loggedInUser.getNeptun();
			this.gender = loggedInUser.getGender();
			this.userId = user.getId().toString();
			this.name = loggedInUser.getName();
			this.roles = new ArrayList<>();
			for (GrantedAuthority role : roles) {
				this.roles.add(role.getAuthority());
			}
		}
	}
}

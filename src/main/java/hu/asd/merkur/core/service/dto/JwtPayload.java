package hu.asd.merkur.core.service.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtPayload {

	private String userId;
	private String name;
	private String neptun;
	private String email;
	private String gender;
	private String currentUserRoleId;
	private List<String> userRoles;

}

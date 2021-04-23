package hu.asd.merkur.auth;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;

import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.common.persist.entity.UserRole;
import hu.asd.merkur.core.service.JwtService;

public class AuthenticationHelper {

	public static final String COOKIE_JWT_NAME = "merkur-jwt";

	public static UserRole getCurrentRole(Collection<GrantedAuthority> authorities) {
		UserRole userRole = null;
		// to test kcssk only
		Iterator<GrantedAuthority> it = authorities.iterator();
		while (it.hasNext()) {
			UserRole currentRole = ((UserRole) it.next());
			if ("KCSSK".equals(currentRole.getDormitory().getShortName())) {
				userRole = currentRole;
				break;
			}
		}
		it = authorities.iterator();
		if (userRole == null && it.hasNext()) {
			userRole = (UserRole) it.next();
		}
		return userRole;
	}

	public static void setJwtCookie(HttpServletResponse response, JwtService jwtService, User user, UUID userRoleId) {
		String jwtToken = jwtService.createJwt(user, userRoleId);
		Cookie c = new Cookie(COOKIE_JWT_NAME, jwtToken);
		c.setPath("/");
		c.setHttpOnly(true);
		response.addCookie(c);
	}
}

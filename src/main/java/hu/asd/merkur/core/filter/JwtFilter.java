package hu.asd.merkur.core.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import hu.asd.merkur.auth.AuthenticationHelper;
import hu.asd.merkur.auth.MerkurAuthUser;
import hu.asd.merkur.common.persist.entity.UserRole;
import hu.asd.merkur.common.service.UserRoleService;
import hu.asd.merkur.core.configuration.MerkurRequestContext.MerkurRequestContextHolder;
import hu.asd.merkur.core.service.JwtService;
import hu.asd.merkur.core.service.dto.JwtPayload;
import hu.asd.merkur.core.service.dto.MerkurUserDetails;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final Logger log = LogManager.getLogger(JwtFilter.class);

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private MerkurRequestContextHolder reqCtxHolder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		try {

			String jwt = getJwtFromRequest(request);
			if (jwt == null) {
				chain.doFilter(request, response);
				return;
			}

			JwtPayload tokenContent = jwtService.getValidUserDataFromJwt(jwt);

			Optional<String> currentUserRoleOpt = tokenContent.getUserRoles()//
					.stream()//
					.filter(ur -> ur.equals(tokenContent.getCurrentUserRoleId()))//
					.findFirst();
			if (currentUserRoleOpt.isEmpty()) {
				log.warn("invalid user-role-id");
				chain.doFilter(request, response);
				return;
			}

			UserRole currentUserRole = userRoleService
					.getInitializedUserRole(UUID.fromString(currentUserRoleOpt.get()));

			reqCtxHolder.setRole(currentUserRole.getRole());
			reqCtxHolder.setDormitory(currentUserRole.getDormitory());

			GrantedAuthority currentRole = new SimpleGrantedAuthority("ROLE_" + currentUserRole.getRole().getRole());

			MerkurAuthUser authUser = new MerkurAuthUser(UUID.fromString(tokenContent.getUserId()),
					tokenContent.getName(), tokenContent.getNeptun(), tokenContent.getEmail(),
					tokenContent.getGender());
			MerkurUserDetails userDetails = new MerkurUserDetails(authUser, currentRole);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, Arrays.asList(currentRole));
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			log.error("Error in JWT filter", e);
		}

		chain.doFilter(request, response);
	}

	private final String getJwtFromRequest(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(AuthenticationHelper.COOKIE_JWT_NAME)) {
				return cookie.getValue();
			}
		}

		String authHeader = httpRequest.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			authHeader.substring(7);
		}

		return null;
	}

}

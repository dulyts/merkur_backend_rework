package hu.asd.merkur.auth.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import hu.asd.merkur.auth.AuthenticationHelper;
import hu.asd.merkur.auth.controller.dto.AuthenticatedResponse;
import hu.asd.merkur.common.controller.dto.UserRoleChangeDTO;
import hu.asd.merkur.common.persist.entity.UserRole;
import hu.asd.merkur.common.persist.repository.DormitoryRepository;
import hu.asd.merkur.common.persist.repository.UserRoleRepository;
import hu.asd.merkur.core.configuration.MerkurRequestContext.MerkurRequestContextHolder;
import hu.asd.merkur.core.service.JwtService;
import hu.asd.merkur.core.service.dto.MerkurUserDetails;

@Transactional(readOnly = true)
public class AbstractAuthenticationController {

	private static final Logger log = LogManager.getLogger(AbstractAuthenticationController.class);

	@Autowired
	protected AuthenticationManager authenticationManager;
	@Autowired
	protected DormitoryRepository dormitoryRepository;
	@Autowired
	protected UserRoleRepository userRoleRepository;
	@Autowired
	protected JwtService jwtService;

	@Autowired
	private MerkurRequestContextHolder merkurContext;

	@GetMapping
	public AuthenticatedResponse session(HttpSession session) {

		System.out.println(merkurContext);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return new AuthenticatedResponse(null);
		}
		MerkurUserDetails userDetail = null;
		if (authentication.getDetails() instanceof MerkurUserDetails) {
			userDetail = (MerkurUserDetails) authentication.getDetails();
		}
		if (authentication.getPrincipal() instanceof MerkurUserDetails) {
			userDetail = (MerkurUserDetails) authentication.getPrincipal();
		}
		if (userDetail != null) {
			return new AuthenticatedResponse(userDetail);
		}
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			log.info(authentication);
		}
		return new AuthenticatedResponse(null);
	}

	@PostMapping("/changeRole")
	public void changeRole(@RequestBody UserRoleChangeDTO data, HttpServletResponse response) throws Exception {
		Optional<UserRole> ur = userRoleRepository.findById(UUID.fromString(data.getUserRoleId()));
		if (ur.isEmpty()) {
			throw new Exception("Do not have permission to selected dormiory and role combination!");
		}
		AuthenticationHelper.setJwtCookie(response, jwtService, ur.get().getUser(), ur.get().getId());
	}

}

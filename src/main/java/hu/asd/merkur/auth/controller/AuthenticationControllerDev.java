package hu.asd.merkur.auth.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.auth.AuthenticationHelper;
import hu.asd.merkur.auth.controller.dto.AuthenticatedResponse;
import hu.asd.merkur.auth.controller.dto.Credentials;
import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.common.persist.repository.UserRepository;
import hu.asd.merkur.core.service.JwtService;

@RestController
@RequestMapping("/session")
@Profile("!release")
public class AuthenticationControllerDev extends AbstractAuthenticationController {

	private static final Logger log = LogManager.getLogger(AuthenticationControllerDev.class);

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepo;

	@PostMapping
	public AuthenticatedResponse login(@RequestBody Credentials credentials, HttpServletResponse response) {

		User u = userRepo.login(credentials.getUsername());

//		Authentication authentication = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
//				credentials.getPassword());
//		SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authentication));
//		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//		MerkurUserDetails userDetail = (MerkurUserDetails) loggedInUser.getPrincipal();
//		log.debug(userDetail);
//
//		if (userDetail.getAuthorities().size() == 0) {
//			throw new DisabledException("not-enough-right");
//		}
//
//		AuthenticatedResponse loginResponse = new AuthenticatedResponse(userDetail);
		AuthenticationHelper.setJwtCookie(response, jwtService, u,
				AuthenticationHelper.getCurrentRole(new ArrayList<>(u.getRoles())).getId());
//		return loginResponse;
		return null;
	}

}

package hu.asd.merkur.core.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.asd.merkur.auth.MerkurAuthUser;
import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.common.persist.repository.UserRepository;
import hu.asd.merkur.core.service.dto.MerkurUserDetails;

@Service
@Transactional
public class MerkurUserDetailService implements UserDetailsService {

	private Logger log = LogManager.getLogger(MerkurUserDetailService.class);

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User loggedInUser = repository.login(name);
		if (loggedInUser == null) {
			log.info("Not found user with name: {}", name);
			throw new UsernameNotFoundException("no-valid-user");
		}
		MerkurAuthUser authUser = new MerkurAuthUser(loggedInUser);
		MerkurUserDetails user = new MerkurUserDetails(authUser, null);
		return user;
	}
}

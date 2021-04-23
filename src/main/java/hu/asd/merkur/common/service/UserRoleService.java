package hu.asd.merkur.common.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.asd.merkur.common.persist.entity.QUserRole;
import hu.asd.merkur.common.persist.entity.UserRole;
import hu.asd.merkur.common.persist.repository.UserRoleRepository;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class UserRoleService extends ServiceParent<UserRole, QUserRole, UserRoleRepository> {

	@Transactional
	public UserRole getInitializedUserRole(UUID userRoleId) {
		UserRole ur = repository.getUserRoleWithRoleAndDormitory(userRoleId).orElseThrow();
//		Hibernate.initialize(ur.getRole());
//		Hibernate.initialize(ur.getDormitory());
		return ur;
	}
}

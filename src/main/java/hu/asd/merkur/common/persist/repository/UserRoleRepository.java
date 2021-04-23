package hu.asd.merkur.common.persist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.QUserRole;
import hu.asd.merkur.common.persist.entity.UserRole;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface UserRoleRepository extends RepositoryParent<UserRole, QUserRole> {

	@Query("SELECT ur FROM UserRole ur WHERE ur.user.id = ?#{ principal.id } AND ur.dormitory.id = ?1 AND ur.role.id = ?2")
	public UserRole getUserRoleByDormitoryAndRole(UUID dormitoryId, UUID roleId);

	@Modifying
	@Query("delete from UserRole ur where ur.role.role = 'HALLGATO'")
	public void deleteAllHallgatoUserRole();

	@Query("SELECT ur FROM UserRole ur JOIN FETCH ur.role JOIN FETCH ur.dormitory where ur.id = ?1")
	public Optional<UserRole> getUserRoleWithRoleAndDormitory(UUID userRoleId);
}

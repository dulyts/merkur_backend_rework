package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.asd.merkur.common.persist.entity.QUser;
import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface UserRepository extends RepositoryParent<User, QUser> {

	@Query(value = "SELECT u FROM User u WHERE u.id = ?#{ principal.id }")
	public User currentUser();

	@Query(value = "SELECT u.name FROM User u WHERE UPPER(u.neptun)= UPPER(?1)")
	public String getCheckNeptun(@Param(value = "neptun") String neptun);

	public User findByNeptunIgnoreCaseAndEmailIgnoreCase(String neptun, String email);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User findByEmailIgnoreCase(String email);

	@Query("SELECT u FROM User u WHERE UPPER(u.neptun) = UPPER(?1) or UPPER(u.email) = UPPER(?1)")
	public User login(String username);

	@Query("SELECT u FROM User u WHERE UPPER(u.elteId) = UPPER(?1)")
	public User findByElteId(String elteId);

	@Query("SELECT u FROM User u WHERE UPPER(u.neptun) = UPPER(?1) or UPPER(u.email) = UPPER(?1)")
	public User get(@Param(value = "neptunOrEmail") String username);

	@Query("SELECT u FROM User u WHERE UPPER(u.neptun) LIKE UPPER('%' || ?1 || '%') or UPPER(u.name) LIKE UPPER('%' || ?1 || '%')")
	public List<User> neptunOrName(@Param(value = "name") String value);

	@Query("SELECT u FROM User u JOIN u.roles r where r.role.role = 'HALLGATO' AND r.dormitory.id = ?#{@merkur.currentDormitory()} AND u.email not like '*elte.hu'")
	List<User> assignableUsers();

	@Query(""//
			+ " SELECT"//
			+ "   u"//
			+ " FROM"//
			+ "   User u"//
			+ "   JOIN u.roles r"//
			+ " WHERE"//
			+ "   r.role.role = 'NEVELO'"//
			+ "   AND r.dormitory.id = ?#{@merkur.currentDormitory()}")
	List<User> getWatchers();

}

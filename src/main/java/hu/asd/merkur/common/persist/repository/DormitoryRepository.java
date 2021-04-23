package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.Dormitory;
import hu.asd.merkur.common.persist.entity.QDormitory;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface DormitoryRepository extends RepositoryParent<Dormitory, QDormitory> {

	public Dormitory findByShortNameIgnoreCase(@Param(value = "shortName") String shortName);

	@Query("SELECT DISTINCT(r.dormitory) FROM UserRole r where r.user.id = ?#{ principal.id }")
	public List<Dormitory> findAllAccessableDormitory();

	@Query("SELECT d FROM Dormitory d where d.id = ?#{@merkur.currentDormitory()}")
	public Dormitory findCurrentDormitory();

}

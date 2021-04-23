package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.QSystemProperty;
import hu.asd.merkur.common.persist.entity.SystemProperty;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface SystemPropertyRepository extends RepositoryParent<SystemProperty, QSystemProperty> {

	@Query("SELECT p FROM SystemProperty p WHERE p.module.key = ?1 and p.dormitory.id = ?#{@merkur.currentDormitory()}")
	List<SystemProperty> getPropertiesByModuleKey(String moduleKey);
}

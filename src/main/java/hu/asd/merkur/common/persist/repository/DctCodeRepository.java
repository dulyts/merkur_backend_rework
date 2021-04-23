package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.DctCode;
import hu.asd.merkur.common.persist.entity.QDctCode;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface DctCodeRepository extends RepositoryParent<DctCode, QDctCode> {

	@Query("SELECT c FROM DctCode c WHERE UPPER(c.dctCodeType.name) = UPPER(?1) AND UPPER(c.code) = UPPER(?2)")
	DctCode findCode(@Param(value = "codeType") String codeType, @Param(value = "code") String code);

	@Query("SELECT c FROM DctCode c WHERE UPPER(c.dctCodeType.name) = UPPER(?1) ORDER BY order ASC")
	List<DctCode> findCodesByType(@Param(value = "codeType") String codeType);

}

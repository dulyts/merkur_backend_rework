package hu.asd.merkur.washing.persist.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.core.persist.repository.RepositoryParent;
import hu.asd.merkur.washing.persist.entity.QWashingMachine;
import hu.asd.merkur.washing.persist.entity.WashingMachine;

@Repository
public interface WashingMachineRepository extends RepositoryParent<WashingMachine, QWashingMachine> {

	@Query("SELECT m FROM WashingMachine m WHERE m.type.code = 'washing' AND m.place.dormitory.id=?#{@merkur.currentDormitory()}")
	public Page<WashingMachine> findAllWashingMachine(Pageable pageable);

	@Query("SELECT m FROM WashingMachine m WHERE m.type.code = 'dryer' AND m.place.dormitory.id=?#{@merkur.currentDormitory()}")
	public Page<WashingMachine> findAllDryer(Pageable pageable);

	@Query("SELECT m FROM WashingMachine m JOIN FETCH m.place WHERE m.type.code = ?1 AND m.status.code = 'ok' AND m.place.dormitory.id=?#{@merkur.currentDormitory()}")
	public List<WashingMachine> findAllMachineByType(String type);
}

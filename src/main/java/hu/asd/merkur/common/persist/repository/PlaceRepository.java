package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.Place;
import hu.asd.merkur.common.persist.entity.QPlace;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface PlaceRepository extends RepositoryParent<Place, QPlace> {

	@Query("SELECT DISTINCT(r) FROM Place r WHERE r.placeType.code = 'SZOBA' AND r.inactive = false AND r.dormitory.id = ?#{@merkur.currentDormitory()}")
	List<Place> findValidRooms();

	@Query("SELECT r FROM Place r WHERE r.building = ?1 AND r.floor = ?2 AND r.roomNumber = ?3 AND r.dormitory.id = ?#{@merkur.currentDormitory()}")
	Place findRoom(String building, Integer floor, String roomNumber);

	@Query("SELECT r FROM Place r JOIN FETCH r.placeType WHERE r.dormitory.id = ?#{@merkur.currentDormitory()} and r.reservable = true")
	List<Place> getReservablePlaces();// Pageable pageable);

	@Query("    SELECT "//
			+ "     DISTINCT(p) "//
			+ " FROM Place p "//
			+ " WHERE "//
			+ "     p.dormitory.id = ?#{@merkur.currentDormitory()}"//
			+ "     AND UPPER("//
			+ "       CONCAT("//
			+ "         p.building, "//
			+ "         p.floor, "//
			+ "         (CASE WHEN (CAST(p.roomNumber as integer) >= 10) THEN '' ELSE '0' END), "//
			+ "         p.roomNumber "//
			+ "       )"//
			+ "     ) like UPPER(CONCAT('%', ?1,'%'))"//
			+ " ORDER BY p.building, p.floor, p.roomNumber")
	Page<Place> findName(String name, Pageable pageable);

	@Query("SELECT p FROM Place p WHERE p.code = ?1")
	Place findByCode(String code);

}

package hu.asd.merkur.washing.persist.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.core.persist.repository.RepositoryParent;
import hu.asd.merkur.washing.persist.entity.QWashingReservation;
import hu.asd.merkur.washing.persist.entity.WashingReservation;

@Repository
public interface WashingReservationRepository extends RepositoryParent<WashingReservation, QWashingReservation> {

	@Query("SELECT r FROM WashingReservation r WHERE r.createdBy.id = ?#{ principal.id } ORDER BY r.start")
	public Page<WashingReservation> getOwnReservations(Pageable pageable);

	@Query("SELECT r FROM WashingReservation r JOIN FETCH r.machine m JOIN FETCH m.type JOIN FETCH m.place WHERE r.createdBy.id = ?#{ principal.id } AND r.end > CURRENT_TIMESTAMP ORDER BY r.start")
	public List<WashingReservation> getOwnFutureReservation();

	// https://stackoverflow.com/questions/325933/determine-whether-two-date-ranges-overlap
	// SELECT * FROM washing.reservation r WHERE r.from_ts < '2020-01-24 01:30:00'
	// AND r.to_ts > '2020-01-24 00:30:00' AND r.machine_id =
	// '442c80dd-fa80-4a5b-ad7e-43d06d510e41'//
	@Query("SELECT count(r) > 0 FROM WashingReservation r WHERE r.start < ?2 AND r.end > ?1 AND r.machine.id = ?3")
	public boolean isOverlappingReservation(Date start, Date end, UUID machineId);

	@Query("    SELECT "//
			+ "   count(r)"//
			+ " FROM "//
			+ "   WashingReservation r "//
			+ " WHERE "//
			+ "   r.start > ?1 "//
			+ "   AND r.machine.type.id = ?2"//
			+ "   AND r.createdBy.id = ?#{ principal.id }")
	public long countOfWeekReservation(Date weekStart, UUID machineType);

	@Query(value = "SELECT m.name," + //
			"           trunc(sum(EXTRACT(epoch FROM" + //
			"               age(r.to_ts, r.from_ts)" + //
			"           )) / 60)" + //
			"       FROM" + //
			"           washing.machine m left outer join washing.reservation r on m.id = r.machine_id" + //
			"           inner join common.place p on p.id = m.place_id" + //
			"       WHERE" + //
			"           r.from_ts between ?1 and ?2 " + //
			"           AND m.type_id = ?3" + //
			"           AND p.dormitory_id = ?#{@merkur.currentDormitory()}" + //
			"       GROUP BY m.name", nativeQuery = true) //
	public List<Object[]> getMachineUtilisation(Date start, Date end, UUID machineTypeId);

	@Query(value = "SELECT m.name," + //
			"           trunc(sum(EXTRACT(epoch FROM" + //
			"               age(r.to_ts, r.from_ts)" + //
			"           )) / 60)" + //
			"       FROM" + //
			"           washing.machine m left outer join washing.reservation r on m.id = r.machine_id" + //
			"           inner join common.place p on p.id = m.place_id" + //
			"       WHERE" + //
			"           r.from_ts between ?1 and ?2 " + //
			"           AND m.type_id = ?3" + //
			"       GROUP BY m.name", nativeQuery = true) //
	public List<Object[]> getAllDormitoryMachineUtilisation(Date start, Date end, UUID machineTypeId);

	@Query(value = "SELECT c.name," + //
			"           trunc(sum(EXTRACT(epoch FROM" + //
			"               age(r.to_ts, r.from_ts)" + //
			"           )) / 60)" + //
			"       FROM" + //
			"           washing.reservation r inner join washing.machine m on m.id = r.machine_id" + //
			"           inner join common.place p on p.id = m.place_id" + //
			"           inner join common.code c on c.id = m.type_id" + //
			"       WHERE" + //
			"           r.from_ts between ?1 and ?2 " + //
			"           AND p.dormitory_id = ?#{@merkur.currentDormitory()}" + //
			"       GROUP BY c.name", nativeQuery = true) //
	public List<Object[]> getMachineTypeComparisation(Date start, Date end);

	@Query(value = "SELECT c.name," + //
			"           trunc(sum(EXTRACT(epoch FROM" + //
			"               age(r.to_ts, r.from_ts)" + //
			"           )) / 60)" + //
			"       FROM" + //
			"           washing.reservation r inner join washing.machine m on m.id = r.machine_id" + //
			"           inner join common.place p on p.id = m.place_id" + //
			"           inner join common.code c on c.id = m.type_id" + //
			"       WHERE" + //
			"           r.from_ts between ?1 and ?2 " + //
			"       GROUP BY c.name", nativeQuery = true) //
	public List<Object[]> getAllDormitoryMachineTypeComparisation(Date start, Date end);

	@Query(value = "SELECT"//
			+ "         d, h, count(r.id)" //
			+ "     FROM "//
			+ "         generate_series(cast(DATE(?1) as timestamp), cast(DATE(?2) as timestamp), '1 day') d"//
			+ "         cross join GENERATE_SERIES(0, 23) h"//
			+ "         left outer join"//
			+ "             (SELECT"//
			+ "                 r.*"//
			+ "             FROM"//
			+ "                 washing.reservation r"//
			+ "                 left join washing.machine m on r.machine_id = m.id"//
			+ "                 left join common.place p on m.place_id = p.id"//
			+ "             where"//
			+ "                 p.dormitory_id = ?#{@merkur.currentDormitory()}"//
			+ "         ) r on h between EXTRACT(HOUR FROM r.from_ts) and EXTRACT(HOUR FROM r.to_ts) and DATE(r.from_ts) = d"//
			+ "     GROUP BY"//
			+ "         d, h"//
			+ "     ORDER BY"//
			+ "         d, h", nativeQuery = true) //
	public List<Object[]> getMachineUsageHeatMap(Date start, Date end);

	@Query(value = "SELECT"//
			+ "         d, h, count(r.id)" //
			+ "     FROM "//
			+ "         generate_series(cast(DATE(?1) as timestamp), cast(DATE(?2) as timestamp), '1 day') d"//
			+ "         cross join GENERATE_SERIES(0, 23) h"//
			+ "         left outer join"//
			+ "             (SELECT"//
			+ "                 r.*"//
			+ "             FROM"//
			+ "                 washing.reservation r"//
			+ "                 left join washing.machine m on r.machine_id = m.id"//
			+ "                 left join common.place p on m.place_id = p.id"//
			+ "         ) r on h between EXTRACT(HOUR FROM r.from_ts) and EXTRACT(HOUR FROM r.to_ts) and DATE(r.from_ts) = d"//
			+ "     GROUP BY"//
			+ "         d, h"//
			+ "     ORDER BY"//
			+ "         d, h", nativeQuery = true) //
	public List<Object[]> getAllDormitoryMachineUsageHeatMap(Date start, Date end);

	@Query(value = ""//
			+ " with days as ("//
			+ "   SELECT s.a AS day_start, s.a + '24 hour' as day_end FROM generate_series(cast(?1 as timestamp), cast(?2 as timestamp), '1 days') AS s(a)"//
			+ " )"//
			+ " select d2.day_start, (CASE WHEN asd.count is null THEN 0 ELSE asd.count END) * ?3"//
			+ " from days d2"//
			+ " full join ("//
			+ "	  select d.day_start, count(1) from washing.reservation r"//
			+ "	  right outer join days d ON r.to_ts > d.day_start and r.from_ts < d.day_end"//
			+ "   join washing.machine m on r.machine_id = m.id"//
			+ "   join common.place p on p.id = m.place_id"//
			+ "   where "//
			+ "    p.dormitory_id = ?#{@merkur.currentDormitory()}"//
			+ "	  group by day_start	"//
			+ " ) as asd on asd.day_start = d2.day_start", nativeQuery = true)
	public List<Object[]> getDailyReservation(Date intervalStart, Date intervalEnd, int washingPrice);

	@Query(value = ""//
			+ " with days as ("//
			+ "   SELECT s.a AS day_start, s.a + '24 hour' as day_end FROM generate_series(cast(?1 as timestamp), cast(?2 as timestamp), '1 days') AS s(a)"//
			+ " )"//
			+ " select d2.day_start, (CASE WHEN asd.count is null THEN 0 ELSE asd.count END) * ?3"//
			+ " from days d2"//
			+ " full join ("//
			+ "	  select d.day_start, count(1) from washing.reservation r"//
			+ "	  right outer join days d ON r.to_ts > d.day_start and r.from_ts < d.day_end"//
			+ "   join washing.machine m on r.machine_id = m.id"//
			+ "   join common.place p on p.id = m.place_id"//
			+ "   where "//
			+ "    p.dormitory_id = ?#{@merkur.currentDormitory()}"//
			+ "	  group by day_start	"//
			+ " ) as asd on asd.day_start = d2.day_start", nativeQuery = true)
	public List<Object[]> getAllDormitoryDailyReservation(Date intervalStart, Date intervalEnd, int washingPrice);

}

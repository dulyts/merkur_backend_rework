package hu.asd.merkur.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.common.controller.dto.SelectableDTO;
import hu.asd.merkur.common.persist.entity.Place;
import hu.asd.merkur.common.persist.entity.QPlace;
import hu.asd.merkur.common.persist.repository.PlaceRepository;
import hu.asd.merkur.common.service.PlaceService;
import hu.asd.merkur.core.controller.ControllerParent;

@RestController
@RequestMapping("places")
public class PlaceController extends ControllerParent<Place, QPlace, PlaceRepository, PlaceService> {

	@GetMapping("reservablePlaces")
	List<SelectableDTO> getReservablePlaces() {
		return repository.getReservablePlaces().stream().map(e -> new SelectableDTO(e)).collect(Collectors.toList());
	}

//	@GetMapping(path = "/buildings")
//	public List<String> buildings(@QuerydslPredicate(root = Place.class) Predicate predicate) {
//		predicate = ExpressionUtils.allOf(predicate, QPlace.place.dormitory.eq(mRegistry.getSession().getDormitory()),
//				QPlace.place.inactive.eq(Boolean.FALSE));
//		QueryFactory<JPQLQuery<?>> queryFactory = new JPAQueryFactory(em);
//		return queryFactory.query().select(QPlace.place.building).distinct().from(QPlace.place).where(predicate)
//				.fetch();
//	}
//
//	@GetMapping(path = "/floors")
//	public List<Integer> floors(@QuerydslPredicate(root = Place.class) Predicate predicate) {
//		predicate = ExpressionUtils.allOf(predicate, QPlace.place.dormitory.eq(mRegistry.getSession().getDormitory()),
//				QPlace.place.inactive.eq(Boolean.FALSE));
//		QueryFactory<JPQLQuery<?>> queryFactory = new JPAQueryFactory(em);
//		return queryFactory.query().select(QPlace.place.floor).distinct().from(QPlace.place).where(predicate).fetch();
//	}
//
//	@GetMapping(path = "/places")
//	public Iterable<PlaceBaseDTO> places(@QuerydslPredicate(root = Place.class) Predicate predicate) {
//		predicate = ExpressionUtils.allOf(predicate, QPlace.place.dormitory.eq(mRegistry.getSession().getDormitory()));
//		QueryFactory<JPQLQuery<?>> queryFactory = new JPAQueryFactory(em);
//		return queryFactory.query().select(QPlace.place).distinct().from(QPlace.place).where(predicate).fetch().stream()
//				.map(this::convertToBaseDTO).collect(Collectors.toList());
//	}
//
//	@RequestMapping(path = "/rooms")
//	public Iterable<PlaceBaseDTO> rooms(@QuerydslPredicate(root = Place.class) Predicate predicate) {
//		DctCode roomCode = codeRepo.findCode("PLACE_TYPE", "SZOBA");
//		predicate = ExpressionUtils.allOf(predicate, QPlace.place.dormitory.eq(mRegistry.getSession().getDormitory()),
//				QPlace.place.placeType.eq(roomCode), QPlace.place.inactive.eq(Boolean.FALSE));
//		QueryFactory<JPQLQuery<?>> queryFactory = new JPAQueryFactory(em);
//		return queryFactory.query().select(QPlace.place).distinct().from(QPlace.place).where(predicate).fetch().stream()
//				.map(this::convertToBaseDTO).collect(Collectors.toList());
//	}
//
//	@GetMapping(path = "/findByName")
//	public Page<PlaceBaseDTO> getPlaceByName(@RequestParam(name = "placeName") String placeName, Pageable pageable) {
//		return repository.findName(placeName, pageable).map(this::convertToBaseDTO);
//	}

}

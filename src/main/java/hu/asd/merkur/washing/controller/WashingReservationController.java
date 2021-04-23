package hu.asd.merkur.washing.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.core.controller.ControllerParent;
import hu.asd.merkur.washing.controller.dto.WashingReservationDTO;
import hu.asd.merkur.washing.persist.entity.QWashingReservation;
import hu.asd.merkur.washing.persist.entity.WashingReservation;
import hu.asd.merkur.washing.persist.repository.WashingReservationRepository;
import hu.asd.merkur.washing.service.WashingReservationService;

@RestController
@RequestMapping("/washing/reservations")
public class WashingReservationController extends
		ControllerParent<WashingReservation, QWashingReservation, WashingReservationRepository, WashingReservationService> {

	@GetMapping("/ownFutureReservations")
	public List<WashingReservationDTO> getOwnFutureReservations() {
		return repository.getOwnFutureReservation()//
				.stream()//
				.map(e -> new WashingReservationDTO(e))//
				.collect(Collectors.toList());
	}

}

package hu.asd.merkur.washing.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.core.service.ServiceParent;
import hu.asd.merkur.washing.persist.entity.QWashingReservation;
import hu.asd.merkur.washing.persist.entity.WashingReservation;
import hu.asd.merkur.washing.persist.repository.WashingReservationRepository;

@Service
public class WashingReservationService
		extends ServiceParent<WashingReservation, QWashingReservation, WashingReservationRepository> {

}

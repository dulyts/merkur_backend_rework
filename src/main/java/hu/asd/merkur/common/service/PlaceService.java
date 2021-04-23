package hu.asd.merkur.common.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.common.persist.entity.Place;
import hu.asd.merkur.common.persist.entity.QPlace;
import hu.asd.merkur.common.persist.repository.PlaceRepository;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class PlaceService extends ServiceParent<Place, QPlace, PlaceRepository> {

}

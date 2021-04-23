package hu.asd.merkur.washing.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.core.service.ServiceParent;
import hu.asd.merkur.washing.persist.entity.QWashingMachine;
import hu.asd.merkur.washing.persist.entity.WashingMachine;
import hu.asd.merkur.washing.persist.repository.WashingMachineRepository;

@Service
public class WashingMachineService extends ServiceParent<WashingMachine, QWashingMachine, WashingMachineRepository> {

	private static final String WASHING_MACHINE_TYPE = "washing";
	private static final String DRYER_MACHINE_TYPE = "dryer";

	public Iterable<WashingMachine> findAllActiveWashingMachine() {
		return repository.findAllMachineByType(WASHING_MACHINE_TYPE);
	}

	public Iterable<WashingMachine> findAllActiveDryer() {
		return repository.findAllMachineByType(DRYER_MACHINE_TYPE);
	}
}

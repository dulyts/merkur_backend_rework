package hu.asd.merkur.washing.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.core.controller.ControllerParent;
import hu.asd.merkur.washing.controller.dto.WashingMachineDTO;
import hu.asd.merkur.washing.persist.entity.QWashingMachine;
import hu.asd.merkur.washing.persist.entity.WashingMachine;
import hu.asd.merkur.washing.persist.repository.WashingMachineRepository;
import hu.asd.merkur.washing.service.WashingMachineService;

@RestController
@RequestMapping("/washing/machines")
public class WashingMachineController
		extends ControllerParent<WashingMachine, QWashingMachine, WashingMachineRepository, WashingMachineService> {

	@GetMapping("/findAllWashingMachine")
	public List<WashingMachineDTO> findAllWashingMachine() {
		return StreamSupport.stream(service.findAllActiveWashingMachine().spliterator(), false)//
				.map(machine -> new WashingMachineDTO(machine))//
				.collect(Collectors.toList());
	}

	@GetMapping("/findAllDryer")
	public List<WashingMachineDTO> findAllDryer() {
		return StreamSupport.stream(service.findAllActiveDryer().spliterator(), false)//
				.map(machine -> new WashingMachineDTO(machine))//
				.collect(Collectors.toList());
	}
}

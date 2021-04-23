package hu.asd.merkur.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.common.controller.dto.DctCodeDTO;
import hu.asd.merkur.common.persist.entity.DctCode;
import hu.asd.merkur.common.persist.entity.QDctCode;
import hu.asd.merkur.common.persist.repository.DctCodeRepository;
import hu.asd.merkur.common.service.DctCodeService;
import hu.asd.merkur.core.controller.ControllerParent;

@RestController
@RequestMapping("dctCodes")
public class DctCodeController extends ControllerParent<DctCode, QDctCode, DctCodeRepository, DctCodeService> {

	@GetMapping("findCodesByType")
	public List<DctCodeDTO> findCodesByType(@RequestParam("codeType") String codeType) {
		return repository.findCodesByType(codeType).stream().map(e -> new DctCodeDTO(e)).collect(Collectors.toList());
	}
}

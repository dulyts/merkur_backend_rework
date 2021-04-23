package hu.asd.merkur.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.common.controller.dto.UserBaseDataDTO;
import hu.asd.merkur.common.persist.entity.QUser;
import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.common.persist.repository.UserRepository;
import hu.asd.merkur.common.service.UserService;
import hu.asd.merkur.core.controller.ControllerParent;

@RestController
@RequestMapping("users")
public class UserController extends ControllerParent<User, QUser, UserRepository, UserService> {

	@GetMapping("neptunOrName")
	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN', 'ROLE_DOORMAN')")
	public List<UserBaseDataDTO> findByNeptunOrName(@RequestParam("name") String filter) {
		return repository.neptunOrName(filter).stream().map(u -> new UserBaseDataDTO(u)).collect(Collectors.toList());
	}

	@GetMapping("getCheckNeptun")
	public String checkNeptun(@RequestParam("neptun") String neptun) {
		return repository.getCheckNeptun(neptun);
	}
}
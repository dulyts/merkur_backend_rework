package hu.asd.merkur.common.controller.dto;

import hu.asd.merkur.common.persist.entity.User;
import hu.asd.merkur.core.persist.repository.AbstractDTO;
import lombok.Data;

@Data
public class UserBaseDataDTO extends AbstractDTO {

	private String name;
	private String neptun;

	public UserBaseDataDTO(User u) {
		super(u);
		name = u.getName();
		neptun = u.getNeptun();
	}
}

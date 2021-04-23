package hu.asd.merkur.common.controller.dto;

import hu.asd.merkur.common.persist.entity.DctCode;
import hu.asd.merkur.core.persist.repository.AbstractDTO;
import lombok.Data;

@Data
public class DctCodeDTO extends AbstractDTO {

	private String code;
	private String name;
	private String description;
	private Integer order;

	public DctCodeDTO(DctCode e) {
		super(e);
		code = e.getCode();
		name = e.getName();
		description = e.getDescription();
		order = e.getOrder();
	}

}

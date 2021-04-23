package hu.asd.merkur.common.controller.dto;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import hu.asd.merkur.core.persist.repository.AbstractDTO;
import lombok.Data;

@Data
public class SelectableDTO extends AbstractDTO {
	private String prompt;

	public SelectableDTO(AbstractEntity e) {
		super(e);
		prompt = e.getPrompt();
	}

}

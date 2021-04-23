package hu.asd.merkur.core.persist.repository;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;

@Data
public class AbstractDTO {

	private String id;

	public AbstractDTO() {
	}

	public AbstractDTO(AbstractEntity e) {
		this.id = e.getId().toString();
	}

}

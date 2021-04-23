package hu.asd.merkur.common.controller.dto;

import hu.asd.merkur.common.persist.entity.SystemProperty;
import hu.asd.merkur.core.persist.repository.AbstractDTO;
import lombok.Data;

@Data
public class SystemPropertyDTO extends AbstractDTO {

	private String key;
	private String value;
	private String type;

	public SystemPropertyDTO(SystemProperty systemProperty) {
		super(systemProperty);
		this.key = systemProperty.getKey();
		this.value = systemProperty.getValue();
		this.type = systemProperty.getType();
	}

	public SystemPropertyDTO(String key, String value) {
		this.key = key;
		this.value = value;
	}

}

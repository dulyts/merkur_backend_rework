package hu.asd.merkur.washing.controller.dto;

import hu.asd.merkur.core.persist.repository.AbstractDTO;
import hu.asd.merkur.washing.persist.entity.WashingMachine;
import lombok.Data;

@Data
public class WashingMachineDTO extends AbstractDTO {

	private String place;

	public WashingMachineDTO(WashingMachine m) {
		super(m);
		this.place = m.getPlace().getPlaceName();
	}

}

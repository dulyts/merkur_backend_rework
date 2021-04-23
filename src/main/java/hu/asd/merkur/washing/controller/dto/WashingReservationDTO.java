package hu.asd.merkur.washing.controller.dto;

import java.util.Date;

import hu.asd.merkur.core.persist.repository.AbstractDTO;
import hu.asd.merkur.washing.persist.entity.WashingReservation;
import lombok.Data;

@Data
public class WashingReservationDTO extends AbstractDTO {

	private Date start;
	private Date end;
	private String machineType;
	private String machineName;
	private String place;

	public WashingReservationDTO(WashingReservation e) {
		super(e);
		start = e.getStart();
		end = e.getEnd();
		machineType = e.getMachine().getType().getCode();
		machineName = e.getMachine().getName();
		place = e.getMachine().getPlace().getPlaceName();
	}

}

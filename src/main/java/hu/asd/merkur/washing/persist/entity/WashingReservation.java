package hu.asd.merkur.washing.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(exclude = {})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "reservation", schema = "washing")
public class WashingReservation extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "machine_id")
	private WashingMachine machine;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "fromTs", nullable = false)
	private Date start;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "toTs", nullable = false)
	private Date end;

	@Override
	public String getPrompt() {
		return machine.getPrompt() + " [" + start + " - " + end + "]";
	}

}

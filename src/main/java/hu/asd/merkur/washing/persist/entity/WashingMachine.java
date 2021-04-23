package hu.asd.merkur.washing.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hu.asd.merkur.common.persist.entity.DctCode;
import hu.asd.merkur.common.persist.entity.Place;
import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(exclude = {})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "machine", schema = "washing")
public class WashingMachine extends AbstractEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private DctCode type;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id", nullable = false)
	private DctCode status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	@Override
	public String getPrompt() {
		return name;
	}

}

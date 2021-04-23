package hu.asd.merkur.common.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "user", "place" })
@ToString(callSuper = true, exclude = { "user", "place" })
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_room_sw", schema = "common")
public class UserRoom extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = false, referencedColumnName = "id")
	private Place place;

	@CreatedDate
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "move_out_date")
	private Date moveOutDate;

	@Override
	public String getPrompt() {
		return getUser().getPrompt() + " - " + getPlace().getPrompt();
	}

}

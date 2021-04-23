package hu.asd.merkur.common.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "notification", "user" })
@ToString(callSuper = true, exclude = {})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notification_user", schema = "common")
public class NotificationUser extends AbstractEntity {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_id")
	private Notification notification;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "read_date", nullable = true, length = 29)
	private Date readDate;

	@Override
	public String getPrompt() {
		return notification.getPrompt() + " - " + user.getPrompt();
	}

}

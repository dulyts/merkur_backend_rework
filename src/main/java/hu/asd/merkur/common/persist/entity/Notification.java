package hu.asd.merkur.common.persist.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(callSuper = true, exclude = {})
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "notification", schema = "common")
public class Notification extends AbstractEntity {

	@Column(name = "template", nullable = false)
	private String template;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "notification")
	private Set<NotificationUser> users;

	private transient boolean read;

	private transient NotificationUser currentNotificationUser;

	public boolean isRead() {
		NotificationUser user = getCurrentNotificationUser();
		if (user != null && user.getReadDate() != null) {
			return true;
		}
		return false;
	}

	public void setRead(Date date) {
		NotificationUser user = getCurrentNotificationUser();
		if (user != null) {
			user.setReadDate(date);
		}
	}

	// TODO
//	public NotificationUser getCurrentNotificationUser() {
//		UUID loggedInUserId = ((MerkurUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal()).getId();
//		Iterator<NotificationUser> usersIt = users.iterator();
//		while (usersIt.hasNext()) {
//			NotificationUser current = usersIt.next();
//			if (current.getUser().getId().equals(loggedInUserId)) {
//				return current;
//			}
//		}
//		return null;
//	}

	@Override
	public String getPrompt() {
		return template;
	}

}

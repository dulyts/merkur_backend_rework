package hu.asd.merkur.common.persist.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "user" })
@ToString(callSuper = true, exclude = { "user" })
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_role", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
		"dormitory_id", "role_id" }))
public class UserRole extends AbstractDormitoryEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1333650308341014073L;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
	private Role role;

	@Override
	public String getAuthority() {
		return "ROLE_" + getRole().getRole().toUpperCase();
	}

	@Override
	public String getPrompt() {
		return getAuthority();
	}

}

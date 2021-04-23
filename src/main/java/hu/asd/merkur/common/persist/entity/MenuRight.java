package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, includeFieldNames = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "menu_right", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "menu_id",
		"role" }))
public class MenuRight extends AbstractEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", nullable = false, referencedColumnName = "id")
	private Menu menu;

	@Column(name = "role", nullable = false, length = 50)
	private String role;

	@Override
	public String getPrompt() {
		return menu.getPrompt() + " - " + role;
	}

}

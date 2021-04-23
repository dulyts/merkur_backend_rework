package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permission", schema = "common")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "permission_type")
public abstract class AbstractPermission extends AbstractEntity {

	@Column(name = "permission_type", nullable = false, length = 250, insertable = false, updatable = false)
	private String permissionType;

	@Column(name = "entity", nullable = false, length = 250)
	private String entity;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
	private Role role;

}

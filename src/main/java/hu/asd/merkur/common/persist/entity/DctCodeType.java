package hu.asd.merkur.common.persist.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "dctCodes" })
@ToString(exclude = { "dctCodes" })
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "code_type", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class DctCodeType extends AbstractEntity {

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = false, length = 200)
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dctCodeType")
	private Set<DctCode> dctCodes;

	@Override
	public String getPrompt() {
		return name;
	}
}

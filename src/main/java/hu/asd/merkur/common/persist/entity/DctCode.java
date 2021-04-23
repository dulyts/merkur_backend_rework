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

import com.fasterxml.jackson.annotation.JsonBackReference;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "code", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "code",
		"code_type_id" }))
public class DctCode extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code_type_id", nullable = false, referencedColumnName = "id")
	// @JsonIgnore
	@JsonBackReference
	@EqualsAndHashCode.Exclude
	private DctCodeType dctCodeType;

	@Column(name = "code", nullable = false, length = 50)
	private String code;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", nullable = false, length = 200)
	private String description;

	@Column(name = "order")
	private Integer order;

	@Override
	public String getPrompt() {
		return name;
	}

}

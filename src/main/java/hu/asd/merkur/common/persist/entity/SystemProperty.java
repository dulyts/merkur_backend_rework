package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "system_property", schema = "common")
public class SystemProperty extends AbstractDormitoryEntity {

	@Column(name = "key", nullable = false, length = 100)
	private String key;

	@Column(name = "value", nullable = false, length = 250)
	private String value;

	@Column(name = "type", nullable = false, length = 20)
	private String type;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "module_id", nullable = false)
	private Module module;

	@Override
	public String getPrompt() {
		return key + " - " + value;
	}

}

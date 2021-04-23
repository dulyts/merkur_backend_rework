package hu.asd.merkur.common.persist.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "module", schema = "common")
public class Module extends AbstractEntity {

	@Column(name = "key", nullable = false, length = 100)
	private String key;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "dormitory_modul_sw", schema = "common", joinColumns = @JoinColumn(name = "module_id", nullable = true, updatable = false), inverseJoinColumns = @JoinColumn(name = "dormitory_id", nullable = true, updatable = false))
	private Set<Dormitory> dormitoriesToAllow = new HashSet<>(0);

	@Override
	public String getPrompt() {
		return name;
	}

}

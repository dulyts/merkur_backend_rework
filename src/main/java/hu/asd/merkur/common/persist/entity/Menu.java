package hu.asd.merkur.common.persist.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "menu", schema = "common", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "parent_menu_id", "title" }), @UniqueConstraint(columnNames = { "name" }) })
public class Menu extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_menu_id", nullable = true, referencedColumnName = "id")
	private Menu parentMenu;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "title", nullable = false, length = 255)
	private String title;

	@Column(name = "level", nullable = false)
	private Integer level;

	@Column(name = "icon", nullable = true, length = 100)
	private String icon;

	@Column(name = "position", nullable = false)
	private Integer order;

	@OneToMany(mappedBy = "parentMenu", fetch = FetchType.EAGER)
	private List<Menu> subMenu = new ArrayList<>(5);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id", nullable = true, referencedColumnName = "id")
	private Module module;

	@ElementCollection
	@CollectionTable(name = "menu_right", schema = "common", joinColumns = @JoinColumn(name = "menu_id"))
	@Column(name = "role")
	private Set<String> rights = new HashSet<>(0);

	@Override
	public String getPrompt() {
		return title;
	}

}

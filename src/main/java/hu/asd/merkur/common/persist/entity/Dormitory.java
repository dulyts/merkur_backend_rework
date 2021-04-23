package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dormitory", schema = "common")
public class Dormitory extends AbstractEntity {

	@Column(name = "short_name", nullable = false, length = 20)
	private String shortName;

	@Column(name = "name", nullable = false, length = 200)
	private String name;

	@Column(name = "size", nullable = true)
	private Integer size;

	@Column(name = "address", nullable = true, length = 200)
	private String address;

	@Column(name = "news_url", nullable = true, length = 1024)
	private String newsUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_dormitory_id")
	private Dormitory parentDormitory;

	@Override
	public String getPrompt() {
		return shortName;
	}

}

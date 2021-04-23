package hu.asd.merkur.core.persist.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import hu.asd.merkur.common.persist.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@GeneratedValue(generator = "generator")
	@Access(AccessType.PROPERTY)
	private UUID id;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	private User createdBy;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_modified_by_id", nullable = true)
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private User lastModifiedBy;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@LastModifiedDate
	@Audited
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date", nullable = true)
	private Date lastModifiedDate;

	public String getTracker() {
		return getId().toString();
	}

	public abstract String getPrompt();

}

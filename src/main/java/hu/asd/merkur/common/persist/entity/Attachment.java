package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(callSuper = true, exclude = {})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "attachment", schema = "common")
public class Attachment extends AbstractEntity {

	@Column(name = "original_file_name", nullable = false, length = 250)
	private String originalFileName;

	@Column(name = "content_type", nullable = false, length = 250)
	private String contentType;

	@Override
	public String getPrompt() {
		return originalFileName;
	}

}

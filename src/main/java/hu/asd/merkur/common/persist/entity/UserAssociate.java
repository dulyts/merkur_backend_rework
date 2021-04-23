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
@Table(name = "user_associate", schema = "common")
public class UserAssociate extends AbstractDormitoryEntity {

	@Column(name = "neptun", nullable = false, length = 6)
	private String neptun;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "gender_id", nullable = false)
	private DctCode gender;

	@Override
	public String getPrompt() {
		return neptun + " - " + gender.getCode() + " - " + getDormitory().getShortName();
	}

}

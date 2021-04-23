package hu.asd.merkur.common.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "role", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "role" }))
public class Role extends AbstractEntity {
	public static final String SUPERADMIN = "SUPERADMIN";
	public static final String KSZK_VEZETO = "KSZK_VEZETO";
	public static final String OSZTALYVEZETO = "OSZTALYVEZETO";
	public static final String KARBANTARTO_VEZETO = "KARBANTARTO_VEZETO";
	public static final String KARBANTARTO = "KARBANTARTO";
	public static final String DB_ELNOK = "DB_ELNOK";
	public static final String DB_TAG = "DB_TAG";
	public static final String HALLGATO = "HALLGATO";
	public static final String PORTAS = "PORTAS";
	public static final String NEVELO = "NEVELO";
	public static final String NEVELO_VEZETO = "NEVELO_VEZETO";

	public static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";
	public static final String ROLE_KSZK_VEZETO = "ROLE_KSZK_VEZETO";
	public static final String ROLE_OSZTALYVEZETO = "ROLE_OSZTALYVEZETO";
	public static final String ROLE_KARBANTARTO_VEZETO = "ROLE_KARBANTARTO_VEZETO";
	public static final String ROLE_KARBANTARTO = "ROLE_KARBANTARTO";
	public static final String ROLE_DB_ELNOK = "ROLE_DB_ELNOK";
	public static final String ROLE_DB_TAG = "ROLE_DB_TAG";
	public static final String ROLE_HALLGATO = "ROLE_HALLGATO";
	public static final String ROLE_PORTAS = "ROLE_PORTAS";
	public static final String ROLE_NEVELO = "ROLE_NEVELO";
	public static final String ROLE_NEVELO_VEZETO = "ROLE_NEVELO_VEZETO";

	@Column(name = "role", nullable = false, length = 50)
	private String role;

	@Column(name = "role_name", nullable = true, length = 200)
	private String roleName;

	@Override
	public String getPrompt() {
		return roleName;
	}

}

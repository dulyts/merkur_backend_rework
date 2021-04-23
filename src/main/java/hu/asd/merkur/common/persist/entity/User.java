package hu.asd.merkur.common.persist.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "common", uniqueConstraints = { @UniqueConstraint(columnNames = { "neptun" }) })
public class User extends AbstractEntity {

	public static transient final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	public static transient final String TECHNICAL_USER_EMAIL = "technical@asd.hu";

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "elte_id", nullable = false)
	private String elteId;

	@Column(name = "neptun", nullable = true, length = 6)
	private String neptun;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender", nullable = false)
	private DctCode gender;

	@Column(name = "phone_nr", nullable = true, length = 20)
	private String phoneNumber;

	@Column(name = "card_nr", nullable = true, length = 20)
	private String cardNumber;

	@Column(name = "card_code", nullable = true, length = 16)
	private String cardCode;

	@Column(name = "kar", nullable = true, length = 100)
	private String kar;

	@Column(name = "szak", nullable = true, length = 100)
	private String szak;

	@JsonBackReference("rolesBack")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = UserRole.class)
	private Set<UserRole> roles = new HashSet<>();

	// TODO
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@JsonBackReference("roomRequestsBack")
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requester")
//	private Set<RoomRequest> roomRequests = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonBackReference("userRoomsBack")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@OrderBy("created_date DESC")
	@Where(clause = "move_out_date IS NULL")
	private List<UserRoom> userRooms = new ArrayList<>();

	// TODO
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@JsonBackReference("roomRequestPeriodsBack")
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "user_period", schema = "room_assignment", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "period_id", nullable = false))
//	private Set<Period> roomRequestPeriods = new HashSet<>();

//	public RoomRequest getRoomRequest(UUID periodId) {
//		Iterator<RoomRequest> it = roomRequests.iterator();
//
//		while (it.hasNext()) {
//			RoomRequest rr = it.next();
//			if (rr.getOriginal() == false && rr.getPeriod().getId().equals(periodId)) {
//				return rr;
//			}
//		}
//		return null;
//	}

//	public String getRoomNumber() {
//		Hibernate.initialize(userRooms);
//		return userRooms.size() > 0 ? userRooms.get(0).getPlace().getPrompt() : "";
//	}

	@Override
	public String getPrompt() {
		return getName();
	}

}

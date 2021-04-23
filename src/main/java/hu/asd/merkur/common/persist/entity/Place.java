package hu.asd.merkur.common.persist.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "place", schema = "common", uniqueConstraints = @UniqueConstraint(columnNames = { "building", "floor",
		"room_number", "dormitory_id" }))
public class Place extends AbstractDormitoryEntity {

	@Column(name = "building", nullable = true, length = 100)
	private String building;

	@Column(name = "floor", nullable = false)
	private int floor;

	@Column(name = "room_number", nullable = false)
	private String roomNumber;

	@Column(name = "direction", length = 20)
	private String direction;

	@Column(name = "size", nullable = false)
	private int roomSize;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_type_id", nullable = false)
	private DctCode placeType;

	@Column(name = "inactive")
	private boolean inactive = false;

	@Column(name = "reservable", nullable = false)
	private boolean reservable;

	@Column(name = "code", length = 32)
	private String code;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "housing_unit", schema = "room_assignment", joinColumns = @JoinColumn(name = "room_id", nullable = true, updatable = false), inverseJoinColumns = @JoinColumn(name = "connected_room_id", nullable = true, updatable = false))
	private Set<Place> connectedRooms = new HashSet<>(0);

	// TODO
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
//	private Set<Key> keys = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
	@OrderBy("created_date DESC")
	@Where(clause = "move_out_date IS NULL")
	private List<UserRoom> userRooms = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
	@OrderBy("created_date DESC")
	private List<UserRoom> allUsersOfRoom = new ArrayList<>();

	@Override
	public String getPrompt() {
		StringBuffer sb = new StringBuffer();
		if (building != null) {
			sb.append(building.toUpperCase());
		}
		sb.append(floor).append(String.format("%2s", roomNumber).replace(" ", "0"));
		if (!"SZOBA".equalsIgnoreCase(placeType.getCode())) {
			sb.append(" (");
			sb.append(placeType.getName());
			sb.append(")");
		}
		return sb.toString();
	}

	public String getPlaceName() {
		StringBuffer sb = new StringBuffer();
		if (building != null) {
			sb.append(building.toUpperCase());
		}
		sb.append(floor)//
				.append(String.format("%2s", roomNumber).replace(" ", "0"));
		return sb.toString();
	}

	// TODO
//	@JsonIgnore
//	transient HousingUnit housingUnit;
//	@JsonIgnore
//	transient Set<User> users;
//	@JsonIgnore
//	transient Set<User> previousUsers = null;

//	public Set<User> getPreviousUsers() {
//		if (previousUsers != null) {
//			return previousUsers;
//		}
//		Set<User> tmp = new HashSet<>();
//		for (UserRoom ur : allUsersOfRoom) {
//			if (tmp.size() >= roomSize) {
//				break;
//			}
//			tmp.add(ur.getUser());
//		}
//		previousUsers = tmp;
//		return previousUsers;
//	}
//
//	public int getFreeSpace() {
//		if (users == null)
//			return roomSize;
//		return roomSize - users.size();
//	}
//
//	public PlaceDTO getDto() {
//		return new PlaceDTO(building, floor, roomNumber, getPrompt());
//	}

	public String getUsersOfRoom() {
		Hibernate.initialize(userRooms);
		return userRooms.size() > 0
				? userRooms.stream().map(UserRoom::getUser).map(User::getName).collect(Collectors.joining(", "))
				: "";
	}

}

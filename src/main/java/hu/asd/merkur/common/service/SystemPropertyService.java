package hu.asd.merkur.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.asd.merkur.common.persist.entity.QSystemProperty;
import hu.asd.merkur.common.persist.entity.SystemProperty;
import hu.asd.merkur.common.persist.repository.SystemPropertyRepository;
import hu.asd.merkur.core.configuration.MerkurRequestContext.MerkurRequestContextHolder;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class SystemPropertyService extends ServiceParent<SystemProperty, QSystemProperty, SystemPropertyRepository> {

	@Autowired
	private SystemPropertyRepository propertyRepo;

	@Autowired
	private MerkurRequestContextHolder ctxHolder;

	// key =
	// UPPER(dormitory) + "_" +
	// UPPER(moduleKey) + "_" +
	// UPPER(propertyKey)
	private static final Map<String, String> CONTAINER = new HashMap<>();

	public Map<String, String> getProperties() {
		return CONTAINER;
	}

	public static final String DEFAULT_DORMITORY_KEY = "DEFAULT";

	// guest
	public static final String SLEEP_COST = "sleep_cost";
	public static final String MAX_GUEST_PER_MONTH = "max_guest_per_month";
	public static final String MAX_GUEST_PER_RESIDENT = "max_guest_per_resident";
	public static final String SLEEP_TIME_START = "sleep_time_start";
	public static final String SLEEP_TIME_END = "sleep_time_end";

	// watcher
	public static final String WEEKDAY_START_TIME = "weekday_start_time";
	public static final String WEEKDAY_DURATION = "weekday_duration";
	public static final String WEEKEND_START_TIME = "weekend_start_time";
	public static final String WEEKEND_DURATION = "weekend_duration";

	public static final String WASHING_MODULE_KEY = "washing";
	public static final String GUEST_MODULE_KEY = "guest";
	public static final String WATCHER_MODULE_KEY = "watcher";
	static {
		// washing
		// TODO
//		addProperty(DEFAULT_DORMITORY_KEY, WASHING_MODULE_KEY, ReservationValidator.MIN_RESERVATION_MINUTE, "60");
//		addProperty(DEFAULT_DORMITORY_KEY, WASHING_MODULE_KEY, ReservationValidator.MAX_RESERVATION_MINUTE,
//				new Integer(4 * 60).toString());
//		addProperty(DEFAULT_DORMITORY_KEY, WASHING_MODULE_KEY, ReservationValidator.MAX_RESERVATION_PER_WEEK, "999");
//		addProperty(DEFAULT_DORMITORY_KEY, WASHING_MODULE_KEY, ReservationValidator.DISTANCE_LENGTH_STEP, "30");
//		addProperty(DEFAULT_DORMITORY_KEY, WASHING_MODULE_KEY, ReservationValidator.START_TIME_STEP, "15");

		// guest
		addProperty(DEFAULT_DORMITORY_KEY, GUEST_MODULE_KEY, SLEEP_COST, "1000");
		addProperty(DEFAULT_DORMITORY_KEY, GUEST_MODULE_KEY, MAX_GUEST_PER_MONTH, "9999");
		addProperty(DEFAULT_DORMITORY_KEY, GUEST_MODULE_KEY, MAX_GUEST_PER_RESIDENT, "10");
		addProperty(DEFAULT_DORMITORY_KEY, GUEST_MODULE_KEY, SLEEP_TIME_START, "23:00");
		addProperty(DEFAULT_DORMITORY_KEY, GUEST_MODULE_KEY, SLEEP_TIME_END, "7:00");

		// watcher
		addProperty(DEFAULT_DORMITORY_KEY, WATCHER_MODULE_KEY, WEEKDAY_START_TIME, "17:00");
		addProperty(DEFAULT_DORMITORY_KEY, WATCHER_MODULE_KEY, WEEKDAY_DURATION, "14:00");
		addProperty(DEFAULT_DORMITORY_KEY, WATCHER_MODULE_KEY, WEEKEND_START_TIME, "7:00");
		addProperty(DEFAULT_DORMITORY_KEY, WATCHER_MODULE_KEY, WEEKEND_DURATION, "24:00");
	}

	public String getProperty(String moduleKey, String key) {
		String dormitoryKey = getDormitoryKey();

		if (CONTAINER.containsKey(getKey(dormitoryKey, moduleKey, key))) {
			return CONTAINER.get(getKey(dormitoryKey, moduleKey, key));
		}
		return CONTAINER.get(getKey(DEFAULT_DORMITORY_KEY, moduleKey, key));
	}

	public Integer getIntProperty(String moduleKey, String key) {
		return Integer.parseInt(getProperty(moduleKey, key));
	}

	public Long getLongProperty(String moduleKey, String key) {
		return Long.parseLong(getProperty(moduleKey, key));
	}

	public void updateAllProperties() {
		String dormitoryKey = getDormitoryKey();
		List<SystemProperty> properties = propertyRepo.findAll();
		for (SystemProperty property : properties) {
			addProperty(dormitoryKey, property.getModule().getKey(), property.getKey(), property.getValue());
		}
	}

	public void updateModuleProperties(String moduleKey) {
		String dormitoryKey = getDormitoryKey();
		List<SystemProperty> properties = propertyRepo.getPropertiesByModuleKey(moduleKey);
		for (SystemProperty property : properties) {
			addProperty(dormitoryKey, moduleKey, property.getKey(), property.getValue());
		}
	}

	private static void addProperty(String dormitoryKey, String moduleKey, String key, String value) {
		CONTAINER.put(getKey(dormitoryKey, moduleKey, key), value);
	}

	private static String getKey(String dormitoryKey, String moduleKey, String key) {
		return dormitoryKey.toUpperCase() + "_" + moduleKey.toUpperCase() + "_" + key.toUpperCase();
	}

	private String getDormitoryKey() {
		return ctxHolder.getDormitory().getShortName();
	}

}

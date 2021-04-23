package hu.asd.merkur.common.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.common.controller.dto.SystemPropertyDTO;
import hu.asd.merkur.common.persist.entity.QSystemProperty;
import hu.asd.merkur.common.persist.entity.SystemProperty;
import hu.asd.merkur.common.persist.repository.SystemPropertyRepository;
import hu.asd.merkur.common.service.SystemPropertyService;
import hu.asd.merkur.core.controller.ControllerParent;

@RestController
@RequestMapping("systemProperties")
public class SystemPropertyController
		extends ControllerParent<SystemProperty, QSystemProperty, SystemPropertyRepository, SystemPropertyService> {

	@Transactional
	@GetMapping
	public Map<String, Map<String, SystemPropertyDTO>> getSystemProperties() {
		Map<String, Map<String, SystemPropertyDTO>> result = getDefaults();
		for (SystemProperty prop : repository.findAll()) {
			String moduleKey = prop.getModule().getKey();
			if (!result.containsKey(moduleKey)) {
				result.put(moduleKey, new HashMap<>());
			}
			result.get(moduleKey).put(prop.getKey(), new SystemPropertyDTO(prop));
		}
		return result;
	}

	private Map<String, Map<String, SystemPropertyDTO>> getDefaults() {
		Map<String, Map<String, SystemPropertyDTO>> result = new HashMap<>();
		for (Entry<String, String> prop : service.getProperties().entrySet()) {
			String[] propKeyArray = prop.getKey().split("_");
			if (!propKeyArray[0].equals(SystemPropertyService.DEFAULT_DORMITORY_KEY)) {
				continue;
			}
			String moduleKey = propKeyArray[1].toLowerCase();
			if (!result.containsKey(moduleKey)) {
				result.put(moduleKey, new HashMap<>());
			}
			result.get(moduleKey).put(getKey(prop.getKey()),
					new SystemPropertyDTO(getKey(prop.getKey()), prop.getValue()));
		}
		return result;
	}

	private static String getKey(String text) {
		return text.substring(ordinalIndexOf(text, "_", 2) + 1).toLowerCase();
	}

	private static int ordinalIndexOf(String str, String substr, int n) {
		int pos = str.indexOf(substr);
		while (--n > 0 && pos != -1)
			pos = str.indexOf(substr, pos + 1);
		return pos;
	}

}

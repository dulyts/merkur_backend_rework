package hu.asd.merkur.common.controller.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.asd.merkur.common.persist.entity.Menu;
import lombok.Data;

@Data
public class MenuItem {
	private static Map<String, String> iconConverter = new HashMap<>();
	static {
		iconConverter.put("ion-ios-home", "home");
		iconConverter.put("ion-tshirt", "tshirt");
		iconConverter.put("ion-flag", "flag");
		iconConverter.put("fa fa-clock-o", "clock");
		iconConverter.put("ion-pie-graph", "chart-bar");
		iconConverter.put("fa fa-eye", "eye");
		iconConverter.put("ion-gear-a", "cogs");
		iconConverter.put("ion-lock-combination", "door-closed");
		iconConverter.put("fa fa-key", "key");
		iconConverter.put("fa fa-bed", "bed");
		iconConverter.put("fa fa-envelope-o", "envelope");
		iconConverter.put("fa fa-building-o", "building");
		iconConverter.put("ion-person", "user");
		iconConverter.put("ion-wrench", "wrench");
	}

	private String path;
	private String title;
	private String icon;
	private List<MenuItem> children;

	public MenuItem(Menu menu) {
		path = menu.getName();
		title = menu.getTitle();
		icon = iconConverter.getOrDefault(menu.getIcon(), "expand");
	}

}

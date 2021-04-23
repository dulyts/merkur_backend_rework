package hu.asd.merkur.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hu.asd.merkur.common.controller.dto.MenuItem;
import hu.asd.merkur.common.persist.entity.Menu;
import hu.asd.merkur.common.persist.entity.QMenu;
import hu.asd.merkur.common.persist.repository.MenuRepository;
import hu.asd.merkur.common.service.MenuService;
import hu.asd.merkur.core.configuration.MerkurRequestContext.MerkurRequestContextHolder;
import hu.asd.merkur.core.controller.ControllerParent;

@RestController
@RequestMapping("menus")
public class MenuController extends ControllerParent<Menu, QMenu, MenuRepository, MenuService> {

	@Autowired
	private MerkurRequestContextHolder reqCtx;

	@RequestMapping(method = RequestMethod.GET)
	@Transactional
	public List<MenuItem> getMenuHierarchy(HttpSession session, HttpServletRequest request) {
		List<Menu> root = repository.findRootMenuItems();
		for (Menu menu : root) {
			if (!Hibernate.isInitialized(menu.getSubMenu())) {
				Hibernate.initialize(menu.getSubMenu());
			}
		}
		List<MenuItem> menus = processMenus(root, request);
		return menus;
	}

	private List<MenuItem> processMenus(List<Menu> subMenus, HttpServletRequest request) {
		if (subMenus == null)
			return null;
		List<MenuItem> resultMenus = new ArrayList<>();
		for (Menu subMenu : subMenus) {
			boolean hasRight = hasRoleToMenu(subMenu.getRights(), request) && hasModuleEnabled(subMenu);
			if (hasRight) {
				MenuItem menuItem = new MenuItem(subMenu);
				List<MenuItem> subMenuItems = processMenus(subMenu.getSubMenu(), request);
				if (!subMenuItems.isEmpty()) {
					menuItem.setChildren(subMenuItems);
				}
				resultMenus.add(menuItem);
			}
		}

		return resultMenus;
	}

	private boolean hasRoleToMenu(Set<String> menuRights, HttpServletRequest request) {
		for (String right : menuRights) {
			if (right.equals("ROLE_" + reqCtx.getRole().getRole())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasModuleEnabled(Menu menu) {
		if (menu.getModule() == null)
			return true;
		return menu.getModule().getDormitoriesToAllow().contains(reqCtx.getDormitory());
	}
}

package hu.asd.merkur.common.persist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.asd.merkur.common.persist.entity.Menu;
import hu.asd.merkur.common.persist.entity.QMenu;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

@Repository
public interface MenuRepository extends RepositoryParent<Menu, QMenu> {

	// @EntityGraph(value = "Menu.menuTree", type = EntityGraphType.LOAD)
	// @EntityGraph(attributePaths = { "subMenus" })
	@Query("SELECT m FROM Menu m WHERE m.parentMenu = null ORDER BY m.order")
	public List<Menu> findRootMenuItems();

}

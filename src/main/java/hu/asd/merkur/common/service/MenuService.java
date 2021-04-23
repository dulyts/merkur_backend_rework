package hu.asd.merkur.common.service;

import org.springframework.stereotype.Service;

import hu.asd.merkur.common.persist.entity.Menu;
import hu.asd.merkur.common.persist.entity.QMenu;
import hu.asd.merkur.common.persist.repository.MenuRepository;
import hu.asd.merkur.core.service.ServiceParent;

@Service
public class MenuService extends ServiceParent<Menu, QMenu, MenuRepository> {

}

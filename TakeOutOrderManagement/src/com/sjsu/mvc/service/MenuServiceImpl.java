package com.sjsu.mvc.service;

import java.util.List;

import javax.persistence.Entity;

import com.sjsu.mvc.dao.MenuServiceDAO;
import com.sjsu.mvc.model.Menu;

public class MenuServiceImpl implements MenuService {
	
	private MenuServiceDAO menuServiceDAO;


	public MenuServiceDAO getMenuServiceDAO() {
		return menuServiceDAO;
	}

	public void setMenuServiceDAO(MenuServiceDAO menuServiceDAO) {
		this.menuServiceDAO = menuServiceDAO;
	}

	@Override
	public List<Entity> getEnabledMenuList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getEnabledMenuList();
	}

	@Override
	public List<Entity> getDisabledMenuList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getDisabledMenuList();
	}

	@Override
	public List<Entity> getFullMenuList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getFullMenuList();
	}

	@Override
	public boolean createMenu(Menu menu) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createMenu(menu);

	}

	@Override
	public boolean deleteMenu(int id) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.deleteMenu(id);

	}

	@Override
	public boolean enableMenu(int id) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.enableMenu(id);

	}

}

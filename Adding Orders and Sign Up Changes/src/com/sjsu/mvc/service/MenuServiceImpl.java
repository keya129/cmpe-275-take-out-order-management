package com.sjsu.mvc.service;


import java.util.List;
import javax.persistence.Entity;

import com.google.appengine.api.datastore.Key;
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
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.getMenuList();

	}


	@Override
	public boolean createMenu(Menu menu) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createMenu(menu);

	}

	@Override
	public boolean deleteMenu(String id) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.deleteMenu(id);

	}

	@Override
	public boolean enableMenu(String id) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.enableMenu(id);

	}

	@Override
	public List<Menu> getMenubyCat(String cat) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.getMenubyCat(cat);
	}

}

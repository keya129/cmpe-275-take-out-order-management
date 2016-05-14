package com.sjsu.mvc.dao;

import java.util.List;

import javax.persistence.Entity;

import com.sjsu.mvc.model.Menu;

public interface MenuServiceDAO {
	
	public boolean createMenu(Menu menu);

	public boolean deleteMenu(int id);

	public boolean enableMenu(int id);

	public List<Menu> getMenuList();

}

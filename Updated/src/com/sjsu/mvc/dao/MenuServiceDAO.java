package com.sjsu.mvc.dao;

import java.util.List;

import javax.persistence.Entity;

import com.google.appengine.api.datastore.Key;
import com.sjsu.mvc.model.Menu;

public interface MenuServiceDAO {
	
	public boolean createMenu(Menu menu);

	public boolean deleteMenu(String id);

	public boolean enableMenu(String id);

	public List<Menu> getMenuList();
	
	public List<Menu> getMenubyCat(String cat);

}

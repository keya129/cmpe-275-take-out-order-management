package com.sjsu.mvc.service;

import java.util.List;

import javax.persistence.Entity;

import com.sjsu.mvc.model.Menu;

public interface MenuService {
	
	public boolean createMenu(Menu menu);

	public boolean deleteMenu(int id);

	public boolean enableMenu(int id);

	public List<Entity> getEnabledMenuList();

	public List<Entity> getDisabledMenuList();

	public List<Entity> getFullMenuList();

}

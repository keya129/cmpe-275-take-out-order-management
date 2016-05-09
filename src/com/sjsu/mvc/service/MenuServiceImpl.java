package com.sjsu.mvc.service;

import java.util.List;

import javax.persistence.Entity;

import com.sjsu.mvc.dao.MenuServiceDAO;
import com.sjsu.mvc.model.Appetizer;
import com.sjsu.mvc.model.Desert;
import com.sjsu.mvc.model.Drink;
import com.sjsu.mvc.model.Maincourse;

public class MenuServiceImpl implements MenuService {
	
	private MenuServiceDAO menuServiceDAO;


	public MenuServiceDAO getMenuServiceDAO() {
		return menuServiceDAO;
	}

	public void setMenuServiceDAO(MenuServiceDAO menuServiceDAO) {
		this.menuServiceDAO = menuServiceDAO;
	}

	@Override
	public List<Entity> getAppetizerList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getAppetizerList();
	}

	@Override
	public List<Entity> getDesertList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getDesertList();
	}

	@Override
	public List<Entity> getDrinkList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getDrinkList();
	}

	@Override
	public List<Entity> getMaincourseList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getMaincourseList();
	}

	@Override
	public List<Entity> getDisabledMenuList() {
		// TODO Auto-generated method stub
		return menuServiceDAO.getDisabledMenuList();
	}

	
	@Override
	public boolean createAppetizer(Appetizer appetizer) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createAppetizer(appetizer);

	}

	@Override
	public boolean createDesert(Desert desert) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createDesert(desert);
	}

	@Override
	public boolean createDrink(Drink drink) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createDrink(drink);
	}

	@Override
	public boolean createMaincourse(Maincourse maincourse) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.createMaincourse(maincourse);
	}

	@Override
	public boolean deleteMenu(int id, String categoryType) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.deleteMenu(id, categoryType);

	}

	@Override
	public boolean enableMenu(int id, String categoryType) {
		// TODO Auto-generated method stub
		return this.menuServiceDAO.enableMenu(id, categoryType);

	}

}

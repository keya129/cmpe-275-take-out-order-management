package com.sjsu.mvc.service;

import java.util.List;

import javax.persistence.Entity;

import com.sjsu.mvc.model.Appetizer;
import com.sjsu.mvc.model.Desert;
import com.sjsu.mvc.model.Drink;
import com.sjsu.mvc.model.Maincourse;

public interface MenuService {
	public boolean createAppetizer(Appetizer appetizer);

	public boolean createDesert(Desert desert);

	public boolean createDrink(Drink drink);

	public boolean createMaincourse(Maincourse maincourse);

	public boolean deleteMenu(int id, String categoryType);

	public boolean enableMenu(int id, String categoryType);

	public List<Entity> getAppetizerList();

	public List<Entity> getDesertList();

	public List<Entity> getDrinkList();

	public List<Entity> getMaincourseList();

	public List<Entity> getDisabledMenuList();

}

package com.sjsu.mvc.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.PMF;
import com.sjsu.mvc.model.Appetizer;
import com.sjsu.mvc.model.Desert;
import com.sjsu.mvc.model.DisabledMenu;
import com.sjsu.mvc.model.Drink;
import com.sjsu.mvc.model.Maincourse;

public class MenuServiceDAOImpl implements MenuServiceDAO{
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	@Override
	public List<Entity> getAppetizerList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();

		return null;
	}
	
	@Transactional
	@Override
	public List<Entity> getDesertList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		return null;
	}

	@Transactional
	@Override
	public List<Entity> getDrinkList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		return null;
	}

	@Transactional
	@Override
	public List<Entity> getMaincourseList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		return null;
	}
	
	@Transactional
	@Override
	public List<Entity> getDisabledMenuList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		return null;
	}
	
	@Transactional
	@Override
	public boolean createAppetizer(Appetizer appetizer) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			em.persist(appetizer);
			txn.commit();
			return true;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
				return false;
			}
			em.close();
			
		}

	}
	
	@Transactional
	@Override
	public boolean createDesert(Desert desert) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			em.persist(desert);
			txn.commit();
			return true;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
				return false;
			}
			em.close();
			
		}

	}
	
	@Transactional
	@Override
	public boolean createDrink(Drink drink) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			em.persist(drink);
			txn.commit();
			return true;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
				return false;
			}
			em.close();
			
		}
	}
	
	@Transactional
	@Override
	public boolean createMaincourse(Maincourse maincourse) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			em.persist(maincourse);
			txn.commit();
			return true;
		} finally {
			if (txn.isActive()) {
				txn.rollback();
				return false;
			}
			em.close();
			
		}
	}
	
	@Transactional
	@Override
	public boolean deleteMenu(int id, String categoryType) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		DisabledMenu dm = new DisabledMenu();
		switch (categoryType) {
		case "Appetizer":
			try {
				Appetizer appetizer = em.find(Appetizer.class, id);
				dm.setMenuId(appetizer.getMenuId());
				dm.setCategoryType(appetizer.getCategoryType());
				dm.setName(appetizer.getName());
				dm.setCalories(appetizer.getCalories());
				dm.setPrepTime(appetizer.getPrepTime());
				dm.setPrice(appetizer.getPrice());
				dm.setUrl(appetizer.getUrl());

				em.remove(appetizer);
				em.persist(dm);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Maincourse":
			try {
				Maincourse maincourse = em.find(Maincourse.class, id);
				dm.setMenuId(maincourse.getMenuId());
				dm.setCategoryType(maincourse.getCategoryType());
				dm.setName(maincourse.getName());
				dm.setCalories(maincourse.getCalories());
				dm.setPrepTime(maincourse.getPrepTime());
				dm.setPrice(maincourse.getPrice());
				dm.setUrl(maincourse.getUrl());

				em.remove(maincourse);
				em.persist(dm);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Drink":
			try {
				Drink drink = em.find(Drink.class, id);
				dm.setMenuId(drink.getMenuId());
				dm.setCategoryType(drink.getCategoryType());
				dm.setName(drink.getName());
				dm.setCalories(drink.getCalories());
				dm.setPrepTime(drink.getPrepTime());
				dm.setPrice(drink.getPrice());
				dm.setUrl(drink.getUrl());

				em.remove(drink);
				em.persist(dm);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Desert":
			try {
				Desert desert = em.find(Desert.class, id);
				dm.setMenuId(desert.getMenuId());
				dm.setCategoryType(desert.getCategoryType());
				dm.setName(desert.getName());
				dm.setCalories(desert.getCalories());
				dm.setPrepTime(desert.getPrepTime());
				dm.setPrice(desert.getPrice());
				dm.setUrl(desert.getUrl());

				em.remove(desert);
				em.persist(dm);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean enableMenu(int id, String categoryType) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		switch (categoryType) {
		case "Appetizer":
			try {
				DisabledMenu dm = em.find(DisabledMenu.class, id);
				Appetizer appetizer = new Appetizer();
				appetizer.setCategoryType(dm.getCategoryType());
				appetizer.setName(dm.getName());
				appetizer.setCalories(dm.getCalories());
				appetizer.setPrepTime(dm.getPrepTime());
				appetizer.setPrice(dm.getPrice());
				appetizer.setUrl(dm.getUrl());

				em.remove(dm);
				em.persist(appetizer);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Maincourse":
			try {
				DisabledMenu dm = em.find(DisabledMenu.class, id);
				Maincourse maincourse = new Maincourse();
				maincourse.setCategoryType(dm.getCategoryType());
				maincourse.setName(dm.getName());
				maincourse.setCalories(dm.getCalories());
				maincourse.setPrepTime(dm.getPrepTime());
				maincourse.setPrice(dm.getPrice());
				maincourse.setUrl(dm.getUrl());

				em.remove(dm);
				em.persist(maincourse);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Drink":
			try {
				DisabledMenu dm = em.find(DisabledMenu.class, id);
				Drink drink = new Drink();
				drink.setCategoryType(dm.getCategoryType());
				drink.setName(dm.getName());
				drink.setCalories(dm.getCalories());
				drink.setPrepTime(dm.getPrepTime());
				drink.setPrice(dm.getPrice());
				drink.setUrl(dm.getUrl());

				em.remove(dm);
				em.persist(drink);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		case "Desert":
			try {
				DisabledMenu dm = em.find(DisabledMenu.class, id);
				Desert desert = new Desert();
				desert.setCategoryType(dm.getCategoryType());
				desert.setName(dm.getName());
				desert.setCalories(dm.getCalories());
				desert.setPrepTime(dm.getPrepTime());
				desert.setPrice(dm.getPrice());
				desert.setUrl(dm.getUrl());

				em.remove(dm);
				em.persist(desert);
				txn.commit();
				return true;
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					return false;
				}
				em.close();
				
			}
		}
		return false;
	}

}

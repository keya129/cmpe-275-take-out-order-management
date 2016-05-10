package com.sjsu.mvc.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.PMF;
import com.sjsu.mvc.model.DisabledMenu;
import com.sjsu.mvc.model.Menu;

public class MenuServiceDAOImpl implements MenuServiceDAO {
	
	@Transactional
	@Override
	public List<Entity> getFullMenuList() {
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
	public List<Entity> getEnabledMenuList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		return null;
	}

	@Transactional
	@Override
	public boolean createMenu(Menu menu) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			em.persist(menu);
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
	public boolean deleteMenu(int id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		DisabledMenu dm = new DisabledMenu();
		try {
			Menu menu = em.find(Menu.class,id);
			dm.setMenuId(menu.getMenuId());
			dm.setCategoryType(menu.getCategoryType());
			dm.setName(menu.getName());
			dm.setCalories(menu.getCalories());
			dm.setPrepTime(menu.getPrepTime());
			dm.setPrice(menu.getPrice());
			dm.setUrl(menu.getUrl());

			em.remove(menu);
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

	@Transactional
	@Override
	public boolean enableMenu(int id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		txn.begin();
		try {
			DisabledMenu dm = em.find(DisabledMenu.class, id);
			Menu menu = new Menu();
			menu.setCategoryType(dm.getCategoryType());
			menu.setName(dm.getName());
			menu.setCalories(dm.getCalories());
			menu.setPrepTime(dm.getPrepTime());
			menu.setPrice(dm.getPrice());
			menu.setUrl(dm.getUrl());

			em.remove(dm);
			em.persist(menu);
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

}

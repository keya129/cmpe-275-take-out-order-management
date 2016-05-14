package com.sjsu.mvc.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Enumerated;
import javax.persistence.Query;

import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.PMF;
import com.sjsu.mvc.model.Menu;

public class MenuServiceDAOImpl implements MenuServiceDAO {
	
	@Transactional
	@Override
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
	
			javax.persistence.Query   q = em.createQuery("SELECT m from Menu m");
			List<Menu> menu = q.getResultList();
			return menu;
	
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
	
		try {
			em.getTransaction().begin();
			//javax.persistence.Query   q = em.createQuery("DELETE FROM Menu m");
			javax.persistence.Query   q = em.createQuery("UPDATE Menu m SET m.mstatus = 'false' WHERE m.menuid = :id");
			q.setParameter("id",id).executeUpdate();
			//q.executeUpdate();
			em.getTransaction().commit();
			return true;
		}finally {
			
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
			javax.persistence.Query   q = em.createQuery("UPDATE Menu m SET m.mstatus = 'true' WHERE m.menuid = :id");
			q.setParameter("id",id).executeUpdate();
			return true;
		}finally {
			if (txn.isActive()) {
				txn.rollback();
				return false;
			}
			em.close();
		}
	}

}

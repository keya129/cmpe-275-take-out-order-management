package com.sjsu.mvc.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Enumerated;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.PMF;
import com.sjsu.mvc.model.Menu;

public class MenuServiceDAOImpl implements MenuServiceDAO {
	
	@Transactional
	@Override
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
	
			javax.persistence.Query   q = em.createQuery("Select m from Menu m",Menu.class);
			List<Menu> menu = q.getResultList();
			return menu;
	
	}

	@Transactional
	@Override
	public boolean createMenu(Menu menu) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		//EntityTransaction txn = em.getTransaction();
		
		try {
			em.persist(menu);
			return true;
		} finally {
			
			em.close();
		}
	}

	
	@Transactional
	@Override
	public boolean deleteMenu(String id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
	
		try {
		    em.getTransaction().begin();
			Menu m =  (Menu) em.createQuery("Select m from Menu m where m.menuid = :menuid").setParameter("menuid", id).getSingleResult();
			m.setMstatus(false);
		   em.persist(m);
		   em.getTransaction().commit();
		   em.refresh(m); 
		//	em.getTransaction().begin();
			//javax.persistence.Query   q = em.createQuery("DELETE FROM Menu m");
			//javax.persistence.Query   q = em.createNativeQuery("UPDATE Menu m SET m.mstatus = 'false' WHERE m.menuid = :id");
			//q.setParameter("id",id).executeUpdate();
			//q.executeUpdate();
			//em.getTransaction().commit();
			return true;
		}finally {
			if(em.getTransaction().isActive())
			em.getTransaction().rollback();
			em.close();
		}
	}

	@Transactional
	@Override
	public boolean enableMenu(String id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			em.getTransaction().begin();
			Menu m =  (Menu) em.createQuery("Select m from Menu m where m.menuid = :menuid").setParameter("menuid", id).getSingleResult();
			m.setMstatus(true);
		   em.persist(m);
		   em.getTransaction().commit();
		   em.refresh(m); 
			return true;
		}finally {
			em.close();
		}
	}

	@Transactional
	@Override
	public List<Menu> getMenubyCat(String cat) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
	
			javax.persistence.Query   q = em.createQuery("Select m from Menu m where m.category = :cat",Menu.class);
			List<Menu> menu = q.setParameter("cat", cat).getResultList();
			return menu;
	}

}

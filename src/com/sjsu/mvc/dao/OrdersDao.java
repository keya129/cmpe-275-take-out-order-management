package com.sjsu.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.model.Orders;

public class OrdersDao {
	
	
	private String status;


	@Transactional	
	public String createOrder(Orders orders) throws SQLException {
		
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(orders);
			em.getTransaction().commit();
			return "success";
		} finally {
			
			em.close();
		}
		
	}
	@Transactional
	public List<Orders> getOrders(String userid) throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Query q = entityManager.createQuery("SELECT * FROM Orders m WHERE m.userid=:arg1");
			q.setParameter("arg1", userid);
			List<Orders> orders = q.getResultList();
			tx.commit();
			return orders;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return null;
	}
	@Transactional
	public List<Orders> getAllOrders() throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Query q = entityManager.createQuery("SELECT * FROM Orders m");
			List<Orders> orders = q.getResultList();
			tx.commit();
			return orders;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return null;
	}
	
	@Transactional
	public List<String> getAllEmail() throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		DateTime dt = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
		List<String> emails = null;
		try {
			tx.begin();
			List<Orders> orders = this.getAllOrders();
			for(Orders o: orders){
				if(o.getFulfildt().isEqual(dt)){
					emails.add(o.getUserid());
				}
			}
			//Query q = entityManager.createNativeQuery("SELECT m.userid FROM Orders m WHERE m.fulfildt=:arg1");
			//q.setParameter("arg1", );
			//Orders orders = (Orders)q.getSingleResult();
			tx.commit();
			return emails;
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return null;
	}

}

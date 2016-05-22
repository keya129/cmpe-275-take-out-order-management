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
			if (em.getTransaction().isActive())
		    em.getTransaction().rollback();
			em.close();
		}
		
	}
	@Transactional
	public List<Orders> getOrders(String userid) throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Query q = entityManager.createQuery("SELECT m FROM Orders");
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
			Query q = entityManager.createQuery("SELECT m FROM Orders m");
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
	
	/*@Transactional
	/*public List<String> getAllEmail() throws SQLException {
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
	} */
	@Transactional
	public String cancelOrder(String orderid) throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			System.out.println("here");
			Query q = entityManager.createQuery("UPDATE  Orders m SET m.status:='Cancelled' WHERE m.orderid=:arg1");
			q.setParameter("arg1", orderid);
			q.executeUpdate();
			tx.commit();
			return "success";
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return "fail";
	}
	
	@Transactional
	public String inProgressOrder(String orderid) throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Query q = entityManager.createQuery("UPDATE Orders m SET m.status:='In-Progress' WHERE m.orderid=:arg1");
			q.setParameter("arg1", orderid);
			q.executeUpdate();
			tx.commit();
			return "success";
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return "fail";
	}
	
	@Transactional
	public String completedOrder(String orderid) throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			Query q = entityManager.createQuery("UPDATE Orders m SET m.status:='Completed' WHERE m.orderid=:arg1");
			q.setParameter("arg1", orderid);
			q.executeUpdate();
			tx.commit();
			return "success";
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
		return "fail";
	}
	
	@Transactional
	public List<String> getAllEmail() throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		DateTime dt = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
		int minuteOfDay = dt.getMinuteOfDay();
		
		List<String> emails = null;
		try {
			tx.begin();
			List<Orders> orders = this.getAllOrders();
			if(orders != null){
			if(orders.size() > 0){
			for(Orders o: orders){
				int savedminuteOfDay = o.getFulfildt().getMinuteOfDay();
				if(dt.withTimeAtStartOfDay() == o.getFulfildt().withTimeAtStartOfDay() && minuteOfDay == savedminuteOfDay && o.getOstatus().equals("Queued")){
					emails.add(o.getUserid());
					String resp = this.inProgressOrder(o.getOrderid());
				}
			  }
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
	
	@Transactional
	public void trackOrderStatus() throws SQLException {
		EntityManager entityManager = EMFService.get().createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		DateTime dt = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
		int minuteOfDay = dt.getMinuteOfDay();
		
		List<String> emails = null;
		try {
			tx.begin();
			List<Orders> orders = this.getAllOrders();
			if(orders != null){
			if(orders.size() > 0){
			for(Orders o: orders){
				int savedminuteOfDay = o.getPickupdt().getMinuteOfDay();
				if(dt.withTimeAtStartOfDay() == o.getPickupdt().withTimeAtStartOfDay() && minuteOfDay == savedminuteOfDay && o.getOstatus().equals("In-Progress")){
					String resp = this.completedOrder(o.getOrderid());
				}
			 }
			}
			}
			//Query q = entityManager.createNativeQuery("SELECT m.userid FROM Orders m WHERE m.fulfildt=:arg1");
			//q.setParameter("arg1", );
			//Orders orders = (Orders)q.getSingleResult();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			entityManager.close();
		}
	}
}

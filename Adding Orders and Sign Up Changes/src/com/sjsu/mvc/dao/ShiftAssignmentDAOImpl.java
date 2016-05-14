package com.sjsu.mvc.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.TypedQuery;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.model.ShiftAssignment;

public class ShiftAssignmentDAOImpl implements ShiftAssignmentDAO  {

	@Override
	public void createorUpdate(ShiftAssignment s) {
		// TODO Auto-generated method stub
		
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(s);
			System.out.println("persisted");
			em.getTransaction().commit();
		}
		finally {
			em.close();
		}	
	}

	@Override
	public List<ShiftAssignment> getShiftbyEmpid(String empid) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		javax.persistence.Query q1 =em.createQuery("SELECT s FROM ShiftAssignment s"
				+ " where s.empid = :empid ORDER BY s.startdate ASC ");
		List<ShiftAssignment> s = new ArrayList<ShiftAssignment>();
		 s = q1.setParameter("empid", empid).getResultList();
	    System.out.println("The size is" +s.size());
		return s;
	}

	@Override
	public void removeShift(String empid) {
		EntityManager em = EMFService.get().createEntityManager();
		
	/*	try {
		ShiftAssignment s =	em.find(ShiftAssignment.class, empid);
			em.remove(s);
			
		} finally {
			em.close();
		} */
		try {
			em.getTransaction().begin();
		    javax.persistence.Query q1 = em.createQuery("DELETE FROM ShiftAssignment s where s.empid = :empid");
            q1.setParameter("empid", empid).executeUpdate();
            em.getTransaction().commit();
		

		    
		} finally {
			em.close();
		}
	}

}

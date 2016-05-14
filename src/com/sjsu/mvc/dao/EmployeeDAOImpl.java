package com.sjsu.mvc.dao;

import javax.persistence.EntityManager;

import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void createorUpdate(Employee e) {
	
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.persist(e);
		} finally {
			em.close();
		}
	}

	@Override
	public Employee getEmployeeId(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		Employee e = (Employee) em.find(Employee.class, id);
		return e;
	}

	@Override
	public void removeEmployee(String id) {
		// TODO Auto-generated method stub
		EntityManager em = EMFService.get().createEntityManager();
		
		try {
			Employee e = em.find(Employee.class, id);
			em.remove(e);
		} finally {
			em.close();
		}
	}

}

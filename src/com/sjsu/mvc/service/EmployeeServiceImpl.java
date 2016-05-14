package com.sjsu.mvc.service;

import com.sjsu.mvc.dao.EmployeeDAO;
import com.sjsu.mvc.dao.EmployeeDAOImpl;
import com.sjsu.mvc.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO empDAO;
	
	public void setEmployeeDAO(EmployeeDAO newempDAO)
	{
		this.empDAO = newempDAO;
	}
	@Override
	public void createorUpdate(Employee e) {
		// TODO Auto-generated method stub
		this.empDAO.createorUpdate(e);
	}

	@Override
	public Employee getEmployeeId(String id) {
		return this.empDAO.getEmployeeId(id);
	}

	@Override
	public void removeEmployee(String id) {
		this.empDAO.removeEmployee(id);
		
	}

}

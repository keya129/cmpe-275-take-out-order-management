package com.sjsu.mvc.service;

import java.sql.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.sjsu.mvc.dao.ShiftAssignmentDAO;
import com.sjsu.mvc.model.ShiftAssignment;

public class ShiftAssignmentServiceImpl implements ShiftAssignmentService{

	 private ShiftAssignmentDAO shiftDAO;
	 
	public void setShiftAssignmentDAO(ShiftAssignmentDAO newshiftDAO){
		this.shiftDAO = newshiftDAO;
	}
	@Override
	public void createorUpdate(ShiftAssignment s) {
		this.shiftDAO.createorUpdate(s);
		
	}

	@Override
	public List<ShiftAssignment> getShiftbyEmpid(String empid) {
		
		return this.shiftDAO.getShiftbyEmpid(empid);
	}

	@Override
	public void removeShiftbyempid(String id) {
		this.shiftDAO.removeShift(id);
		
	}

}

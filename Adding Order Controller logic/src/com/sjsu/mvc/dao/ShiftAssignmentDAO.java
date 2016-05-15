package com.sjsu.mvc.dao;

import java.sql.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.sjsu.mvc.model.ShiftAssignment;

public interface ShiftAssignmentDAO {
    public void createorUpdate(ShiftAssignment s);
    public List<ShiftAssignment> getShiftbyEmpid(String empid);
    public void removeShift(String id);
	
}

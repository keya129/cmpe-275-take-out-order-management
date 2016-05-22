package com.sjsu.mvc.service;

import java.sql.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.sjsu.mvc.model.ShiftAssignment;

public interface ShiftAssignmentService {
    public void createorUpdate(ShiftAssignment s);
    public List<ShiftAssignment> getShiftbyEmpid(String empid);
    public void removeShiftbyempid(String id);
}

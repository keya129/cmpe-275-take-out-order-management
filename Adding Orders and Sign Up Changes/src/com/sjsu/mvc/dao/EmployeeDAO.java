package com.sjsu.mvc.dao;

import com.sjsu.mvc.model.Employee;

public interface EmployeeDAO {
    public void createorUpdate(Employee e);
    public Employee getEmployeeId(String id);
    public void removeEmployee(String id);
}

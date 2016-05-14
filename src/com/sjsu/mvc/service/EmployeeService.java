package com.sjsu.mvc.service;

import com.sjsu.mvc.model.Employee;

public interface EmployeeService {
    public void createorUpdate(Employee e);
    public Employee getEmployeeId(String id);
    public void removeEmployee(String id);
}

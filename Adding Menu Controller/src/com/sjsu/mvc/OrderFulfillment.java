package com.sjsu.mvc;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.sjsu.mvc.model.ShiftAssignment;
import com.sjsu.mvc.service.EmployeeService;
import com.sjsu.mvc.service.ShiftAssignmentService;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
public class OrderFulfillment {

	private EmployeeService empService;
	

    @Autowired(required=true)
    @Qualifier(value="employeeService")
    public void setEmployeeService(EmployeeService es){
        this.empService = es;
    }
    

    

	

    
    
}

package com.sjsu.mvc.service;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;




public class SchedulerJobs {
	
	private OrderService orderService;

	@Autowired(required = true)
	@Qualifier(value = "orderService")
	public void setMenuService(OrderService os) {
		this.orderService = os;
	}
	
	@Scheduled(fixedRate = 30000)
    public void emailNotification()
    {
        System.out.println("Inside Scheduler");
        
       List<String> emails = orderService.getEmailsList();
       
       //Sending Regular Emails for time intervals
        
    }

}

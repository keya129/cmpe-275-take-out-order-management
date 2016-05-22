package com.sjsu.mvc.service;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.sjsu.mvc.OrderController;
import com.sjsu.mvc.ProfileController;
import com.sjsu.mvc.model.Orders;



public class OrderLogic {
	
	Orders od;
	
	public OrderLogic(Orders od){
		this.od = od;
	}
	
/*	public Orders orderProcess(){
		
		Orders orders = new Orders();

		orders.setPickupdt(od.getPickupdt());
		orders.setUserid(od.getUserid());
		
		DateTime current = new DateTime(DateTimeZone.forID("America/Los_Angeles")); 
		orders.setOrderdt(current);
		orders.setReadydt(od.getPickupdt());
		
		DateTime fulfildt = od.getPickupdt().minusMinutes(od.getTotalpreptime());
		
		orders.setFulfildt(fulfildt);
		orders.setTotalamount(od.getTotalamount());
		
		orders.setOstatus("Queued");
		
		return orders;
	}*/
	
	public DateTime checkPickUp(DateTime pck,int preptime ){
		DateTime pick = new OrderController().detpickup(pck,preptime);
		return pick;
	}
}

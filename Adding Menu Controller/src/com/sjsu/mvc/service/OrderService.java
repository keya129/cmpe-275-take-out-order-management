package com.sjsu.mvc.service;

import java.sql.SQLException;
import java.util.List;

import com.sjsu.mvc.dao.EmployeeDAO;
import com.sjsu.mvc.dao.MenuServiceDAO;
import com.sjsu.mvc.dao.OrdersDao;
import com.sjsu.mvc.model.Orders;



public class OrderService {
	

	private OrdersDao ordersDao;
	
	public OrdersDao getOrdersDao() {
		return ordersDao;
		}
	public void setOrdersDao(OrdersDao newordDAO)
	{
		this.ordersDao = newordDAO;
	}

	
	public List<Orders> getOrders(String userid) {
		// TODO Auto-generated method stub
		try {
			return this.ordersDao.getOrders(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String createOrder(Orders orders) {
		// TODO Auto-generated method stub
		try {
			return this.ordersDao.createOrder(orders);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public List<String> getEmailsList(){
		 try {
				List<String> emaillist = this.ordersDao.getAllEmail();
				//Send notificaitons from here
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;
	}


}

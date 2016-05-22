package com.sjsu.mvc;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.sjsu.mvc.model.Menu;
import com.sjsu.mvc.model.OrderItem;
import com.sjsu.mvc.model.Orders;
import com.sjsu.mvc.model.ShiftAssignment;
import com.sjsu.mvc.service.EmployeeService;
import com.sjsu.mvc.service.OrderLogic;
import com.sjsu.mvc.service.OrderService;
import com.sjsu.mvc.service.ShiftAssignmentService;


@Controller

public class OrderController {

	private OrderService orderService;
    private ShiftAssignmentService shiftService;
 
    
    @Autowired(required=true)
    @Qualifier(value="ShiftAssignmentService")
    public void setShiftAssignmentService(ShiftAssignmentService es){
        this.shiftService = es;
    }
    
	
	@Autowired(required = true)
	@Qualifier(value = "orderService")
	public void setOrderService(OrderService os) {
		this.orderService = os;
	}

	@RequestMapping(value = "/displayOrder", method = { RequestMethod.GET })
	public String login() {
		return "createOrders";
	}
	@RequestMapping(value = "/getallOrders", method = { RequestMethod.GET })
	public String getallOrders(Model model, HttpServletRequest request) {
List<Orders> orders = this.orderService.getAllOrders();
		System.out.println("orders "+orders);
		for(int i = 0; i < orders.size();i++)
		{
			System.out.println(orders.get(i).getTotalamount());
		}
		model.addAttribute("orders", orders);
		return "index";
	} 
	/**
	 * This method is for creating the Menu Item
	 */

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(@RequestParam("userid") String userid,
			@RequestParam("menuCount") String menuCount, 
			@RequestParam("menuid") String menuid,
			@RequestParam("menuname") String menuname,
			@RequestParam("menucategory") String menucategory,
			@RequestParam("menuqty") String menuqty,
			@RequestParam("menuamount") String menuamount,
			@RequestParam("pickup") String pickupS,
			@RequestParam("preptime") String preptime,
			Model model,HttpServletRequest request) {
	System.out.println("cmpe275");	
DateTime dt = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
		
		Orders order = new Orders();
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime pickup = formatter.parseDateTime(pickupS);
		System.out.println("hksdhs"+pickup);	
		
		List<OrderItem> ot = new ArrayList<OrderItem>();
		String[] imname = menuname.split(",");
		String[] icategory = menucategory.split(",");
		String[] iqty = menuqty.split(",");
		String[] iamount = menuamount.split(",");
		String[] imenuid = menuid.split(",");
		String[] ipreptime = preptime.split(",");
		
		int totptime = 0;
		double totalAmount = 0;
		for(int i = 0; i < Integer.parseInt(menuCount); i++){
			OrderItem om = new OrderItem();
			om.setUserid(userid);
			om.setCategory(icategory[i]);
			om.setMname(imname[i]);
			//om.setQty(Integer.parseInt(iqty[i]));
			om.setQty(10);
			om.setRating(0);
			om.setAmount(Double.parseDouble(iamount[i]));
			om.setMenuid(imenuid[i]);
			//om.setMenuid(menuid);
			ot.add(om);
			totptime = totptime+Integer.parseInt(ipreptime[i]);
			//totalAmount = (Double.parseDouble(iamount[i])) * (Integer.parseInt(iqty[i]));
			totalAmount = (Double.parseDouble(iamount[i])) * 10;
		}
		
		order.setUserid(userid);
		order.setOstatus("Queued");
		order.setTotalamount(totalAmount);
		
		DateTime pickupTime = pickup;
		pickupTime = pickupTime.minusMinutes(totptime);
		
		if(pickup.isBefore(dt)){
			model.addAttribute("message", "Order cannot be in the Past");
			return "index";
		}
		if(pickup == null){
			
			DateTime pickupdt = checkPickUp(dt,totptime);
			
		    	model.addAttribute("message", "Food will be avaible at  "+pickupdt);
				return "index";
		    
		}
		
	
		
		DateTime pickupdt = checkPickUp(pickupTime,totptime);
		  if(pickupTime.isBefore(pickupdt)){
		    	model.addAttribute("message", "This PickUP time is not avaiable. Earliest available PickUP time is "+pickupdt);
				return "index";
		    } else {
		    	
		    	order.setPickupdt(pickup);
		    	order.setOrderdt(dt);
		    	order.setFulfildt(pickupTime);
		    	order.setReadydt(pickup);
		    	
	    
	    if (orderService.createOrder(order).equals("success")) {
			model.addAttribute("message", "Order Place Successfully.");
			model.addAttribute("Pickuptime",pickup.toString());
			model.addAttribute("TotalAmount",totalAmount);
			return "index";
		} else {
			model.addAttribute("message", "Transaction Failure");
			return "index";
		}
		
	}
	}
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	public String getOrders(Model model, HttpServletRequest request) throws JSONException, ParseException {
		String ordLst = (String) request.getParameter("path");
	    System.out.println("Json"+ordLst);
	    JSONArray mJsonArray = new JSONArray(ordLst.toString());
	    JSONObject mJsonObject = new JSONObject();
        String date = (String) request.getParameter("date_input");
        String time = (String) request.getParameter("usr_time");
        String datetime = date + " " +time;
        String pattern = "YYYY-MM-dd HH:mm:ss";
        DateTime pickup  = DateTime.parse(datetime, DateTimeFormat.forPattern(pattern));
        System.out.println("Date"+datetime);
        System.out.println("Pick up date"+pickup);
        int totprep = 0;
        double totAmt = 0;
        String username = (String)request.getSession().getAttribute("user"); 
        List<OrderItem> orderItem = new ArrayList<OrderItem>();
        Orders order = new Orders();
 	    UUID idOn = UUID.randomUUID();
 	    String orderid=""+idOn; 
 	    
 	    order.setOrderid(orderid);
 	    order.setUserid(username);
 	    order.setPickupdt(pickup);
 	   
	    for (int i = 0; i < mJsonArray.length(); i++) {
	        mJsonObject = mJsonArray.getJSONObject(i);
	      String menu =  mJsonObject.getString("menu");
	      String menuid =  mJsonObject.getString("menuid");
	      double amount = mJsonObject.getDouble("price");
	      String qty = mJsonObject.getString("qty");
	      String pt = mJsonObject.getString("preptime");
	      int preptime = Integer.parseInt(pt);
	      int quantity = Integer.parseInt(qty);
	      String category = mJsonObject.getString("category");   
	       System.out.println("Menu" +menu);
	       System.out.println("Menu id" +menuid);
	       System.out.println("price "+amount );
	       System.out.println("user"+username);
	       System.out.println("Prep  time"+preptime);
	       System.out.println("Category" +category);
	    	   OrderItem orderitem = new OrderItem();
	    	   orderitem.setMname(menu);
	    	   orderitem.setMenuid(menuid);
	    	    orderitem.setQty(quantity);
	    	    orderitem.setAmount(amount * quantity);
	    	    
	    	    totprep =  totprep + preptime;
	       
	            totAmt =  (totAmt + (amount * quantity));
	            orderitem.setUserid(username);
	            orderitem.setCategory(category);
	            orderItem.add(orderitem);
	           
	            
	          
	    }
	    order.setTotalamount(totAmt);
	    order.setOrderItems(orderItem);
	    System.out.println("Total PrepTime" +totprep);
	    System.out.println("Total Amount" +totAmt);

	    DateTime today = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
	    DateTime startTime = pickup.minusMinutes(totprep);
	    System.out.println("The start time is" +startTime);
	    order.setOrderdt(today);
		if(pickup.isBefore(today)){
			System.out.println("Order in the Past");
			order.setOstatus("NotAccepted");
			model.addAttribute("message", "Order cannot be in the Past");
	    	model.addAttribute("orderid", order.getOrderid());
	    	model.addAttribute("userid", order.getUserid());
	    	model.addAttribute("pickup", order.getPickupdt());
	    	model.addAttribute("fdate", order.getFulfildt());
	    	model.addAttribute("readydt", order.getReadydt());
	    	model.addAttribute("orderdt", order.getOrderdt());
	    	model.addAttribute("orderstatus", order.getOstatus());
	    	model.addAttribute("orderitems",order.getOrderItems());
			return "receipt";
		}
		if(pickup.toString().isEmpty()){
			order.setOstatus("NotAccepted");
			DateTime pickupdt = checkPickUp(today,totprep);
			   System.out.println("The food will be available at" +pickupdt);
		    	model.addAttribute("message", "Food will be avaible at  "+pickupdt);
		    	model.addAttribute("orderid", order.getOrderid());
		    	model.addAttribute("userid", order.getUserid());
		    	model.addAttribute("pickup", order.getPickupdt());
		    	model.addAttribute("fdate", order.getFulfildt());
		    	model.addAttribute("readydt", order.getReadydt());
		    	model.addAttribute("orderdt", order.getOrderdt());
		    	model.addAttribute("orderstatus", order.getOstatus());
		        model.addAttribute("orderitems",order.getOrderItems());
				return "receipt";
		    
		} 
		else {
		DateTime pickupdt = checkPickUp(pickup,totprep);
		  if(pickup.isBefore(pickupdt)){
			    order.setOstatus("NotAccepted");
		        System.out.println("The earliest pickup time is " +pickupdt);
		    	model.addAttribute("message", "This PickUP time is not avaiable. Earliest available PickUP time is "+pickupdt);
		    	model.addAttribute("orderid", order.getOrderid());
		    	model.addAttribute("userid", order.getUserid());
		    	model.addAttribute("pickup", order.getPickupdt());
		    	model.addAttribute("fdate", order.getFulfildt());
		    	model.addAttribute("readydt", order.getReadydt());
		    	model.addAttribute("orderdt", order.getOrderdt());
		    	model.addAttribute("orderstatus", order.getOstatus());
		     	model.addAttribute("orderitems",order.getOrderItems());
				return "receipt";
		    } else {
		    	
		    	order.setPickupdt(pickup);
		    	order.setFulfildt(pickup);
		    	order.setReadydt(pickup); 
		    	order.setOstatus("Queued");
		    	System.out.println("The pickupTime is "+pickup);
		    	
		    }
		}
	    if (this.orderService.createOrder(order).equals("success")) {
			model.addAttribute("message", "Order Place Successfully.");
	    	model.addAttribute("orderid", order.getOrderid());
	    	model.addAttribute("userid", order.getUserid());
	    	model.addAttribute("pickup", order.getPickupdt());
	    	model.addAttribute("fdate", order.getFulfildt());
	    	model.addAttribute("readydt", order.getReadydt());
	    	model.addAttribute("orderdt", order.getOrderdt());
	    	model.addAttribute("orderstatus", order.getOstatus());
	    	model.addAttribute("orderitems",order.getOrderItems());
			return "receipt";
		} else {
			model.addAttribute("message", "Transaction Failure");
	    	model.addAttribute("orderid", order.getOrderid());
	    	model.addAttribute("userid", order.getUserid());
	    	model.addAttribute("pickup", order.getPickupdt());
	    	model.addAttribute("fdate", order.getFulfildt());
	    	model.addAttribute("readydt", order.getReadydt());
	    	model.addAttribute("orderdt", order.getOrderdt());
	    	model.addAttribute("orderstatus", order.getOstatus());
	    	model.addAttribute("orderitems",order.getOrderItems());
			return "receipt";
		}	  

	}



	public DateTime detpickup(DateTime pickupDate,int preptime){
		
		DateTime time = scheduling(pickupDate, preptime);
		return time;
	}
	public DateTime scheduling(DateTime pickupDate, int preptime){
		int assignment = 0;
		int gap1 =0,gap2 = 0,gap3 = 0,gap =0;
		DateTime time1 = null, time2 = null, time3 = null,time = null;
		List<ShiftAssignment> e1 = new ArrayList<ShiftAssignment>();
		//Determine the schedule for employee 1
		 e1 = this.shiftService.getShiftbyEmpid("1");
		if(!e1.isEmpty())
		{
			assignment = assign(e1,pickupDate,preptime);
		}
		else{
			System.out.println("Assigning to employee 1 as he is free");
		    ShiftAssignment emp1 = new ShiftAssignment();
		    emp1.setempId("1");
		    emp1.setEndDate(pickupDate.plusMinutes(preptime));
		    emp1.setStartDate(pickupDate);
		    this.shiftService.createorUpdate(emp1);
		    assignment = 1;
		}
		if(assignment == 0)
		{
			DateTime endDate = new DateTime(e1.get(e1.size() - 1).getEndDate());
			time1 = endDate.plusMinutes(1);
		    gap1 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
		}
		if(assignment == 0)
		{
			System.out.println("Searching employee 2");
			//Determine the schedule for employee 1
			List<ShiftAssignment> e2 = new ArrayList<ShiftAssignment>();
			 e2 = this.shiftService.getShiftbyEmpid("2");
			if(!e2.isEmpty())
			{
				assignment = assign(e2,pickupDate,preptime);
			}
			else{
				System.out.println("Assigning to employee 2 as he is free");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId("2");
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    this.shiftService.createorUpdate(emp1);
			    assignment = 1;
			}
		
		if(assignment == 0)
		{
			DateTime endDate = new DateTime(e2.get(e2.size() - 1).getEndDate());
			time2 = endDate.plusMinutes(1);
		    gap2 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
		}
		}
		if(assignment == 0)
		{
			System.out.println("Searching employee 3");
			List<ShiftAssignment> e3 = new ArrayList<ShiftAssignment>();
			e3 = this.shiftService.getShiftbyEmpid("3");
			if(!e3.isEmpty())
			{
				assignment = assign(e3,pickupDate,preptime);
			}
			else{
				System.out.println("Assigning to employee 3 as he is free");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId("3");
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    this.shiftService.createorUpdate(emp1);
			    assignment = 1;
			}
			if(assignment == 0)
			{
				DateTime endDate = new DateTime(e3.get(e3.size() - 1).getEndDate());
				time3 = endDate.plusMinutes(1);
			    gap3 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
			}
		}
		
		if(gap1 > gap2)
		{
			time = time2;
			gap = gap2;
		}else {
			time = time1;
			gap = gap1;
		} 
		if(gap > gap3)
		{
			gap = gap3;
			time = time3;
		}
		if(assignment == 1)
		time = pickupDate;
		
		return time;
		
	}
	public int assign(List<ShiftAssignment> emp, DateTime pickupDate, int preptime)
	{
		System.out.println("Checking in between slots");
		for(int i =0 ; i< emp.size() ; i++)
		{
			if(i != emp.size() - 1)
			{
			if(pickupDate.isAfter(new DateTime(emp.get(i).getEndDate())) && 
					pickupDate.plusMinutes(preptime).isBefore(new DateTime(emp.get(i+1).getEndDate()))){
				
				System.out.println("In between slots found");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId(emp.get(i).getempId());
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    this.shiftService.createorUpdate(emp1);
			    System.out.println("Assignment done to :" +emp.get(i).getempId());
			    return 1;
			}
			}else {
				System.out.println("Checking the last slot");
				if(pickupDate.isAfter(new DateTime(emp.get(i).getEndDate())))
				{
					ShiftAssignment emp1 = new ShiftAssignment();
				    emp1.setempId(emp.get(i).getempId());
				    emp1.setEndDate(pickupDate.plusMinutes(preptime));
				    emp1.setStartDate(pickupDate);
				    this.shiftService.createorUpdate(emp1);
				    System.out.println("Assignment done to :" +emp.get(i).getempId());	
				    return 1;
			     }
			}
		}
		return 0;
	}
	public DateTime checkPickUp(DateTime pck,int preptime ){
		DateTime pick = detpickup(pck,preptime);
		return pick;
	}
	
	@RequestMapping(value = "/cancelOrder/{Orderid}", method = RequestMethod.GET)
	 public String cancelOrder(@PathVariable String orderid,Model model, HttpServletRequest request) {
	  System.out.println("here");
	  String response = orderService.cancelOrder(orderid);
	  model.addAttribute("message", response);
	  return "message";
	 }
}
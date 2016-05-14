package com.sjsu.mvc.service;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class OrderDetail implements Serializable{
	private String userid;
	private DateTime pickupdt;
	private double totalamount;
	private List<String> category;
	private List<String> mname;
	private List menuid;
	private List qty;
	private List rating;
	private double amount;
	private int totalpreptime;
	
	

	public int getTotalpreptime() {
		return totalpreptime;
	}

	public void setTotalpreptime(int totalpreptime) {
		this.totalpreptime = totalpreptime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public DateTime getPickupdt() {
		return pickupdt;
	}

	public void setPickupdt(DateTime pickupdt) {
		this.pickupdt = pickupdt;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<String> getMname() {
		return mname;
	}

	public void setMname(List<String> mname) {
		this.mname = mname;
	}

	public List getMenuid() {
		return menuid;
	}

	public void setMenuid(List menuid) {
		this.menuid = menuid;
	}

	public List getQty() {
		return qty;
	}

	public void setQty(List qty) {
		this.qty = qty;
	}

	public List getRating() {
		return rating;
	}

	public void setRating(List rating) {
		this.rating = rating;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

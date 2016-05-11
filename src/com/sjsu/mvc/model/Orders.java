package com.sjsu.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@Column(name = "orderid", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NUM1")
	@SequenceGenerator(name = "SEQ_NUM1", sequenceName = "SEQ_NUM1", allocationSize = 1, initialValue = 1)
	private int orderid;

	@Column(nullable = false)
	private String userid;

	@Column(nullable = false)
	private java.sql.Timestamp pickupdt;

	@Column(nullable = false)
	private java.sql.Timestamp fulfildt;

	@Column(nullable = false)
	private java.sql.Timestamp readydt;

	@Column(nullable = false)
	private java.sql.Timestamp orderdt;

	@Column(nullable = false)
	private double totalamount;

	@Column(nullable = false)
	private String ostatus;

	@OneToMany(mappedBy = "orders", targetEntity = OrderItem.class, fetch = FetchType.EAGER)
	private OrderItem orderItem;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public java.sql.Timestamp getPickupdt() {
		return pickupdt;
	}

	public void setPickupdt(java.sql.Timestamp pickupdt) {
		this.pickupdt = pickupdt;
	}

	public java.sql.Timestamp getFulfildt() {
		return fulfildt;
	}

	public void setFulfildt(java.sql.Timestamp fulfildt) {
		this.fulfildt = fulfildt;
	}

	public java.sql.Timestamp getReadydt() {
		return readydt;
	}

	public void setReadydt(java.sql.Timestamp readydt) {
		this.readydt = readydt;
	}

	public java.sql.Timestamp getOrderdt() {
		return orderdt;
	}

	public void setOrderdt(java.sql.Timestamp orderdt) {
		this.orderdt = orderdt;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getOstatus() {
		return ostatus;
	}

	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

}

package com.sjsu.mvc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {

	@Id
	@Column(name = "menuid", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NUM")
	@SequenceGenerator(name = "SEQ_NUM", sequenceName = "SEQ_NUM", allocationSize = 1, initialValue = 0)
	private int menuid;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String picture;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private int calories;

	@Column(nullable = false)
	private int preptime;

	@Column(nullable = false)
	private java.sql.Timestamp created;

	@Column(nullable = false)
	private boolean mstatus;

	@Column(nullable = false)
	private float temprating;

	@Column(nullable = false)
	private float finalrating;

	@ManyToMany(mappedBy = "menuList", fetch = FetchType.EAGER)
	private List<OrderItem> orderItemList;

	public int getMenuid() {
		return menuid;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getPreptime() {
		return preptime;
	}

	public void setPreptime(int preptime) {
		this.preptime = preptime;
	}

	public java.sql.Timestamp getCreated() {
		return created;
	}

	public void setCreated(java.sql.Timestamp created) {
		this.created = created;
	}

	public boolean isMstatus() {
		return mstatus;
	}

	public void setMstatus(boolean mstatus) {
		this.mstatus = mstatus;
	}

	public float getTemprating() {
		return temprating;
	}

	public void setTemprating(float temprating) {
		this.temprating = temprating;
	}

	public float getFinalrating() {
		return finalrating;
	}

	public void setFinalrating(float finalrating) {
		this.finalrating = finalrating;
	}

}

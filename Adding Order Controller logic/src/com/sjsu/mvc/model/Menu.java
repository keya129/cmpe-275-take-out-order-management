package com.sjsu.mvc.model;

import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

@Entity(name = "Menu")
public class Menu {

	@Id
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String menuid;

	@Column(nullable = false)
	private String category;

	@Column
	private String name;
	
	@Column
	private String picture;

	@Column(nullable = false)
	private double price;


	private long calories;

	private long preptime;

	@Column(nullable = false)
	private java.sql.Timestamp created;

	@Column(nullable = false)
	private boolean mstatus;

	@Column(nullable = false)
	private float temprating;

	@Column(nullable = false)
	private float finalrating;


	public String getMenuid() {
		return menuid;
	}



	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getCategory(){
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

	public long getCalories() {
		return calories;
	}

	public void setCalories(long calories) {
		this.calories = calories;
	}

	public long getPreptime() {
		return preptime;
	}

	public void setPreptime(long preptime) {
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

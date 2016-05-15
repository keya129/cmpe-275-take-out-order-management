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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name = "OrderItem")
public class OrderItem {

	@Id
	@PrimaryKey
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String id;

	@Column(nullable = false)
	private String userid;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String mname;
	
	@Column(nullable = false)
	private int qty;
	
	@Column(nullable = false)
	private float rating;

	@Column(nullable = false)
	private double amount;
	
	
	@Column(nullable = false)
	private String menuid;

   /* @ManyToOne(optional=false)
    @JoinColumn(name="orderid",referencedColumnName="orderid")
    private Orders orders;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="ORDER_DETAIL",
            joinColumns=
            @JoinColumn(name="orderid", referencedColumnName="orderid"),
      inverseJoinColumns=
            @JoinColumn(name="menuid", referencedColumnName="menuid")
    )
    private List<Menu> menuList;*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	/*public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}    
	*/
    
    
}


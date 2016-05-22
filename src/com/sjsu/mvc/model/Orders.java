package com.sjsu.mvc.model;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.google.appengine.api.datastore.Key;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@Column(name = "orderid", nullable = false)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	@PrimaryKey
	private String orderid;

	@Column(nullable = false)
	private String userid;

	@Column(nullable = false)
	private Date pickupdt;

	@Column(nullable = false)
	private Date fulfildt;

	@Column(nullable = false)
	private Date readydt;

	@Column(nullable = false)
	private Date orderdt;
	
	@Column(nullable = false)
	private double totalamount;
	
	@Column(nullable = false)
	private String ostatus;

	
	@OneToMany(mappedBy = "orders",  cascade = CascadeType.ALL)
	@Basic(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems; 
	
	
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	} 

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public DateTime getPickupdt() {
		DateTime pickup = new DateTime(pickupdt);
		return pickup;
	}

	public void setPickupdt(DateTime pickupdt) {
		this.pickupdt = pickupdt.toDate();
	}

	public DateTime getFulfildt() {
		DateTime fullfil = new DateTime(fulfildt);
		return fullfil;
	}

	public void setFulfildt(DateTime fulfildt) {
		
		this.fulfildt = fulfildt.toDate();
	}

	public DateTime getReadydt() {
		DateTime ready = new DateTime(readydt);
		return ready;
	}

	public void setReadydt(DateTime readydt) {
		this.readydt = readydt.toDate();
	}

	public DateTime getOrderdt() {
		DateTime order = new DateTime(orderdt);
		return order;
	}

	public void setOrderdt(DateTime orderdt) {
		this.orderdt = orderdt.toDate();
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
	
	/*@OneToMany
    @JoinTable(name="Orditem", 
          joinColumns=@JoinColumn(name="orderid"),
          inverseJoinColumns=@JoinColumn(name="id"))
    private Collection<OrderItem> orderItem;*/

	/*public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}*/
	
	
	
	
}

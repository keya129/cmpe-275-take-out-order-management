package com.sjsu.mvc.model;

import java.util.Date;

import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;

import com.google.appengine.api.datastore.Key;


@Entity
public class ShiftAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private Key id;
    
    // Shift Start Date 
    private Date startdate;
    
    //Shift End Date 
    private Date enddate;
    
    //Request ID 
    private String requestId;
    
    //Status 
    private String status;
    
    //Employee Info
    private String empid;
    
    public Key getId() {
    	return id;
    }
    public void setId(Key newId){
       this.id = newId;	
    }
    
    public Date getStartDate() {
    	return startdate;
    }
    public void setStartDate(DateTime newDate){
    	
       this.startdate = newDate.toDate();
       System.out.println("The start date received is" +this.startdate);
    }
    
    public Date getEndDate() {
    	return enddate;
    }
    public void setEndDate(DateTime newDate){
    	
       this.enddate = newDate.toDate();
       System.out.println("The end date received is" +this.enddate);
    } 
    public String getrequestId() {
    	return requestId;
    }
    public void setRequestId(String newrequestId){
       this.requestId = newrequestId;
    }   
    public String getStatus() {
    	return status;
    }
    public void setStatus(String newStatus){
       this.status = newStatus;	
    }
    public void setempId(String empid){
    	this.empid = empid;
    	
    }
    public String getempId()
    {
    	return this.empid;
    }
}

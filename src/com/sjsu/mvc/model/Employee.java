package com.sjsu.mvc.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity bean with JPA annotations
 * @author Bhavana Bhasker 
 * 
 */
@Entity(name = "Employee")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
 
    private String firstName;

    private String lastName;
  
    public String getId() {
    	return id;
    }
    public void setId(String newId){
       this.id = newId;	
    }
    
    
    public String getFirstName(){
    	return firstName;
    }
    public void setFirstName(String newfirstname){
    	this.firstName = newfirstname;
    }
    public String getLastName() {
    	return lastName;
    }
    public void setLastName(String newlastname)
    {
    	this.lastName = newlastname;
    }
 
}
package com.sjsu.mvc.model;
 
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.google.appengine.api.datastore.Key;
/**
 * Entity bean with JPA annotations
 * @author
 *
 */
@PersistenceCapable
public class Profile {
 
	
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
	@Persistent 
    private String firstName;
	@Persistent
    private String lastName;
	@PrimaryKey
	@Persistent
    private String email;
	@Persistent
    private String password;
	@Persistent
    private String userType;

    public Long getId() {
    	return id;
    }
    public void setId(Long newId){
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
    public String getUSer() {
    	return lastName;
    }
    public void setUSer(String user)
    {
    	this.userType = user;
    }

    public String getEmail() {
    	return email;
    }
    public void setEmail(String newemail){
    	this.email = newemail;
    }
    public String getPassword(){
    	return password;
    }
    public void setPassword(String newaddress){
    	this.password = newaddress;
    }
     
}
package com.sjsu.mvc.model;
 
import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.google.appengine.api.datastore.Key;
/**
 * Entity bean with JPA annotations
 * @author
 *
 */


@Entity
public class Profile implements Serializable{
 
	@PrimaryKey
	@Column
	@Id
	private String email;
		
    @Column
    private String firstName;
    
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String lastName;

    @Column
    private String password;
    
    @Column
    private String userType;

    public void setEmail(String newemail){
    	this.email = newemail;
    }
  
    public String getEmail() {
    	return this.email;
    }

    public String getFirstName(){
    	return this.firstName;
    }
    public void setFirstName(String newfirstname){
    	this.firstName = newfirstname;
    }
    public String getLastName() {
    	return this.lastName;
    }
    public void setLastName(String newlastname)
    {
    	this.lastName = newlastname;
    }
    public String getUSer() {
    	return this.lastName;
    }
    public void setUSer(String user)
    {
    	this.userType = user;
    }

    public String getPassword(){
    	return this.password;
    }
    public void setPassword(String newaddress){
    	this.password = newaddress;
    }
    public Long getid(){
    	return this.id;
    }
    public void setid(Long newid){
    	this.id = newid;
    }

     
}

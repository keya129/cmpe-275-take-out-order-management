package com.sjsu.mvc.service;
 
import java.util.List;
 
import org.springframework.stereotype.Service;

import com.sjsu.mvc.dao.PersonDAO;
import com.sjsu.mvc.model.Profile;

 
@Service
public class PersonServiceImpl implements PersonService {
     
    private PersonDAO personDAO;
 
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
 
    @Override
    public void createorUpdate(Profile p) {
        this.personDAO.createorUpdate(p);
    }
  
    @Override
    public Profile getPersonById(String id) {
        return this.personDAO.getPersonById(id);
    }
 
    @Override
    public void remove(String id) {
        this.personDAO.remove(id);
    }
 
    @Override
    public void checkLogin(String email,String password){
    	this.personDAO.checkLogin(email,password);
    }
}
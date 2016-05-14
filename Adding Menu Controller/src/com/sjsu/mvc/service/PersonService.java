package com.sjsu.mvc.service;
 
import java.util.List;
 

import com.sjsu.mvc.model.Profile;
 
public interface PersonService {
 
    public void createorUpdate(Profile p);
    public Profile getPersonById(String id);
    public void remove(String id);
    public boolean checkLogin(String email,String password);
}

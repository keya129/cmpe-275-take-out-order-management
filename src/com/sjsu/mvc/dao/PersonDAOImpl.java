package com.sjsu.mvc.dao;
 
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.persistence.*;
import javax.jdo.Query;


import com.sjsu.mvc.PMF;
import com.sjsu.mvc.EMFService;
import com.sjsu.mvc.model.Profile;
 

public class PersonDAOImpl implements PersonDAO {
     	 
    @Override
    public Profile getPersonById(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		/*Query q = pm.newQuery(Profile.class);
		q.setFilter("id == id");
	    List<Profile> results = (List<Profile>) q.execute();
        Profile p = new Profile();
        p = results.get(Integer.parseInt(id));*/
        Profile p = pm.getObjectById(Profile.class, id);
        return p;
    }

	@Override
	public void createorUpdate(Profile  p) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			System.out.println(p.getFirstName());
			pm.makePersistent(p);
		} finally {
			pm.close();
		}
	    
	}
	public boolean checkLogin(String email,String password){
		EntityManager em = EMFService.get().createEntityManager();
		
		try{
			if(em.find( Profile.class, email )!=null){
				 Profile p = em.find( Profile.class, email );
				 System.out.println(p.getFirstName());
					 if(p.getPassword().equals(password)){
						 System.out.println("Returned true");
						 return true;
					 }
				 return false;
			 }
			 else{
				 return false;
			 }
			 
		}finally{
			em.close();
		}
		        
	}
	@Override
	public void remove(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {

			Profile p = pm.getObjectById(Profile.class,id);
			pm.deletePersistent(p);

		} finally {
			pm.close();
		}

		
	}
 
  /*  @Override
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person p = (Person) session.load(Person.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details="+p);
    }*/
 
}
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
    	EntityManager em = EMFService.get().createEntityManager();
		//PersistenceManager pm = PMF.get().getPersistenceManager();
		/*Query q = pm.newQuery(Profile.class);
		q.setFilter("id == id");
	    List<Profile> results = (List<Profile>) q.execute();
        Profile p = new Profile();
        p = results.get(Integer.parseInt(id));*/
        //Profile p = pm.getObjectById(Profile.class, id);
    		
    	javax.persistence.Query q = em.createQuery("Select p from Profile p where p.id  = :id");
    	Profile p = (Profile) q.setParameter("id",id).getResultList();  	
        return p;
    }

	@Override
	public void createorUpdate(Profile  p) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			System.out.println("Creating" +p.getEmail());
			
			em.getTransaction().begin();
			em.persist(p);
			System.out.println("persisted");
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	    
	}
	public boolean checkLogin(String email,String password){
		EntityManager em = EMFService.get().createEntityManager();
		
		try{
			
			Profile p = em.find(Profile.class, email);
			System.out.println(p.getEmail());
			System.out.println(p.getid());
			if(p.getEmail().equals(email))
			return true;
			else 
		    return false;
		/*	if(em.find( Profile.class, email )!=null){
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
			 } */
			 
		}finally{
			em.close();
		}
		        
	}
	@Override
	public void remove(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {

			javax.persistence.Query q= em.createQuery("Delete from Profile p where p.id = :id");
            q.setParameter("id",id).executeUpdate();		

		} finally {
			em.close();
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
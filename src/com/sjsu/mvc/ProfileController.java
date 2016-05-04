package com.sjsu.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sjsu.mvc.model.Profile;
import com.sjsu.mvc.service.PersonService;



 
@Controller
public class ProfileController {
     
    private PersonService personService;
     
    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }
	/**
	 * This method is for creating the Profile
	 */
	@RequestMapping(value = "/profile", method = {RequestMethod.GET,RequestMethod.DELETE})
	public String profile() {
		return "createProfile";

	}
	/**
	 * This method is for login
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.DELETE})
	public String login() {
		return "login";

	}

	/**
	 * This method is for retrieving the page based on the parameter passed
	 */
	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	public String profile(@PathVariable String userId, Model model, HttpServletRequest request) { 
		String format = request.getParameter("brief");
		String result = "Profile";
		try{
		Profile p = personService.getPersonById(userId);
		model.addAttribute("homePageForm",p);
		}
		catch(Exception e)
		{
	    model.addAttribute("userIdNotFound", userId);
	    result = "error";
		}
		if(format != null && "true".equalsIgnoreCase(format)) {
			model.addAttribute("json", true);
		} else {
			model.addAttribute("htmlPage", true);
		}
		return result;
	}
	
	@RequestMapping(value = "/profile/{userId}", method = RequestMethod.DELETE)
	 public @ResponseBody String profileDelete(@PathVariable String userId, HttpServletRequest request ){
	    String result = "/profile";
		try{
			Profile p = personService.getPersonById(userId);
			personService.remove(userId);
		}
		catch(Exception e)		
		{
		    result = "error";
		 } 
			
		return result;
	}
	@RequestMapping(value="/profile",params = {"firstName", "lastName", "email", "password"},method = RequestMethod.POST) 
	public ModelAndView createOrUpdate(@PathVariable String userId,HttpServletRequest request) {
        Profile p = new Profile();
        p.setFirstName(request.getParameter("firstName"));
        p.setLastName(request.getParameter("lastName"));
        p.setEmail(request.getParameter("email"));
        p.setPassword(request.getParameter("password"));
         System.out.println(p);
		personService.createorUpdate(p);
		return new ModelAndView("redirect:/profile/"); 
	} 
	@RequestMapping(value="/login",
			params = {"email", "password"},
			method = RequestMethod.POST)
	public String checkLogin(Model model, HttpServletRequest request) { 
		String emailstr = request.getParameter("email");
		
		return "login";
	}
}
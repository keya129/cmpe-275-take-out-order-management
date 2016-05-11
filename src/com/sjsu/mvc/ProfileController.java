package com.sjsu.mvc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
import com.sjsu.mvc.MailService;


 
@Controller
public class ProfileController {
     
    private PersonService personService;
    @Autowired
	private JavaMailSender mailSender;
    Long  s;

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
		return "index";

	}
	/**
	 * This method is for login
	 */
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.DELETE})
	public String login(HttpServletRequest request) {
		return "index";

	}
	@RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.DELETE})
	public String index(HttpServletRequest request) {
		return "index";

	}
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.DELETE})
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("Log out");
		return "index";

	}

	@RequestMapping(value = "/login/{eid}", method = {RequestMethod.GET})
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("Errormsg","User is not validated");
		return "/index";

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
	    String result = "/index";
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
	@RequestMapping(value="/profile/{email}" ,method = RequestMethod.POST) 
	public void sendEmail(@PathVariable String email,Model model,HttpServletRequest request){
        Profile p = new Profile();
         s=Math.round(Math.random() * 100000);

        System.out.println(email);
        //MailService ms=new MailService();
       // MailService.sendSimpleMail();
        final String emailTo = email+".com";
	    final String subject = "Verification" ;
	    final String yourmailid = "bhavana.bhasker@gmail.com";
	    final String message = "Hi Your code "+s;
	    // for logging
	    System.out.println("emailTo: " + emailTo);
	    System.out.println("subject: " + subject);
	    System.out.println("Your mail id is: "+yourmailid);
	    System.out.println("message: " + message);
	    

	    mailSender.send(new MimeMessagePreparator() {

	    @Override
	    public void prepare(MimeMessage mimeMessage) throws Exception {
	    MimeMessageHelper messageHelper = new MimeMessageHelper(
	    mimeMessage, true, "UTF-8");
	    messageHelper.setTo(emailTo);
	    messageHelper.setSubject(subject);
	    messageHelper.setReplyTo(yourmailid);
	    messageHelper.setText(message);
	    
	     
	    }
	    });

	}
	@RequestMapping(value="/profile",params = {"firstName", "lastName", "email", "password","code"},method = RequestMethod.POST) 
	public String createOrUpdate(Model model,HttpServletRequest request) {
        Profile p = new Profile();
        p.setFirstName(request.getParameter("firstName"));
        p.setLastName(request.getParameter("lastName"));
        p.setEmail(request.getParameter("email"));
        p.setPassword(request.getParameter("password"));
        System.out.println(p);
        System.out.println("value of s "+s);
        System.out.println("value of code "+request.getParameter("code"));

        if(request.getParameter("code").equals(s.toString())){
        personService.createorUpdate(p);
		model.addAttribute("Errormsg","User is created");}
        else{
    		model.addAttribute("Errormsg","User is not created as verification code did not match. Try Again");
    		}
	
        

		return "index";
	} 
	@RequestMapping(value="/login",params = {"email", "password"},method = RequestMethod.POST)
	public String checkLogin(Model model, HttpServletRequest request,@CookieValue(value = "user", defaultValue = "customer") HttpServletResponse response) { 
		String emailstr = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("Email is "+emailstr+" Pass is" +password);
		if(emailstr.equals("admin") && password.equals("admin")){
	        request.getSession().setAttribute("user", "admin");
	        response.addCookie(new Cookie("user", "admin"));
	        System.out.println(request.getSession().getAttribute("user"));
	        return "index";
		}
		else if(personService.checkLogin(emailstr, password)){
			System.out.println("validated");
		        request.getSession().setAttribute("user", "customer");
		        response.addCookie(new Cookie("user", "customer"));
		        

		        System.out.println(request.getSession().getAttribute("user"));
			return "index";}
		else{
			System.out.println("not validated");
			model.addAttribute("Errormsg","User is not validated");

		return "index";}
	}
}
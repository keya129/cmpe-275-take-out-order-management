package com.sjsu.mvc;

import java.util.ArrayList;
import java.util.List;
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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
import org.joda.time.Minutes;
import com.sjsu.mvc.model.Employee;
import com.sjsu.mvc.model.Profile;
import com.sjsu.mvc.model.ShiftAssignment;
import com.sjsu.mvc.service.EmployeeService;
import com.sjsu.mvc.service.PersonService;
import com.sjsu.mvc.service.ShiftAssignmentService;



 
@Controller
public class ProfileController {
     
    private PersonService personService;
    private ShiftAssignmentService shiftService;
    private EmployeeService empService;
    
    @Autowired
	private JavaMailSender mailSender;
    Long  s;

    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }
    
    @Autowired(required=true)
    @Qualifier(value="ShiftAssignmentService")
    public void setShiftAssignmentService(ShiftAssignmentService es){
        this.shiftService = es;
    }
    
    @Autowired(required=true)
    @Qualifier(value="employeeService")
    public void setemployeeService(EmployeeService es){
        this.empService = es;
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
	public String checkLogin(Model model, HttpServletRequest request,HttpServletResponse response) { 
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
	@RequestMapping(value="/employee" , method = RequestMethod.GET)
	public String createEmployee() {
		DateTime today = new DateTime(DateTimeZone.forID("America/Los_Angeles"));
		DateTime time = detpickup(today,30);
		return "emp";
		
	}
	public DateTime detpickup(DateTime pickupDate,int preptime){
		
		DateTime time = scheduling(pickupDate, preptime);
		return time;
	}
	public DateTime scheduling(DateTime pickupDate, int preptime){
		int assignment = 0;
		int gap1 =0,gap2 = 0,gap3 = 0,gap =0;
		DateTime time1 = null, time2 = null, time3 = null,time = null;
		List<ShiftAssignment> e1 = new ArrayList<ShiftAssignment>();
		//Determine the schedule for employee 1
		 e1 = this.shiftService.getShiftbyEmpid("1");
		if(!e1.isEmpty())
		{
			assignment = assign(e1,pickupDate,preptime);
		}
		else{
			System.out.println("Assigning to employee 1 as he is free");
		    ShiftAssignment emp1 = new ShiftAssignment();
		    emp1.setempId("1");
		    emp1.setEndDate(pickupDate.plusMinutes(preptime));
		    emp1.setStartDate(pickupDate);
		    assignment = 1;
		}
		if(assignment == 0)
		{
			DateTime endDate = new DateTime(e1.get(e1.size() - 1).getEndDate());
			time1 = endDate.plusMinutes(1);
		    gap1 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
		}
		if(assignment == 0)
		{
			System.out.println("Searching employee 2");
			//Determine the schedule for employee 1
			List<ShiftAssignment> e2 = new ArrayList<ShiftAssignment>();
			 e2 = this.shiftService.getShiftbyEmpid("2");
			if(!e2.isEmpty())
			{
				assignment = assign(e2,pickupDate,preptime);
			}
			else{
				System.out.println("Assigning to employee 2 as he is free");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId("2");
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    assignment = 1;
			}
		
		if(assignment == 0)
		{
			DateTime endDate = new DateTime(e2.get(e2.size() - 1).getEndDate());
			time2 = endDate.plusMinutes(1);
		    gap2 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
		}
		}
		if(assignment == 0)
		{
			System.out.println("Searching employee 3");
			List<ShiftAssignment> e3 = new ArrayList<ShiftAssignment>();
			e3 = this.shiftService.getShiftbyEmpid("3");
			if(!e3.isEmpty())
			{
				assignment = assign(e3,pickupDate,preptime);
			}
			else{
				System.out.println("Assigning to employee 3 as he is free");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId("3");
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    assignment = 1;
			}
			if(assignment == 0)
			{
				DateTime endDate = new DateTime(e3.get(e3.size() - 1).getEndDate());
				time3 = endDate.plusMinutes(1);
			    gap3 = Minutes.minutesBetween(pickupDate,endDate).getMinutes();
			}
		}
		
		if(gap1 > gap2)
		{
			time = time2;
			gap = gap2;
		}else {
			time = time1;
			gap = gap1;
		} 
		if(gap > gap3)
		{
			gap = gap3;
			time = time3;
		}
		if(assignment == 1)
		time = pickupDate;
		
		return time;
		
	}
	public int assign(List<ShiftAssignment> emp, DateTime pickupDate, int preptime)
	{
		System.out.println("Checking in between slots");
		for(int i =0 ; i< emp.size() ; i++)
		{
			if(i != emp.size() - 1)
			{
			if(pickupDate.isAfter(new DateTime(emp.get(i).getEndDate())) && 
					pickupDate.plusMinutes(preptime).isBefore(new DateTime(emp.get(i+1).getEndDate()))){
				
				System.out.println("In between slots found");
			    ShiftAssignment emp1 = new ShiftAssignment();
			    emp1.setempId(emp.get(i).getempId());
			    emp1.setEndDate(pickupDate.plusMinutes(preptime));
			    emp1.setStartDate(pickupDate);
			    System.out.println("Assignment done to :" +emp.get(i).getempId());
			    return 1;
			}
			}else {
				System.out.println("Checking the last slot");
				if(pickupDate.isAfter(new DateTime(emp.get(i).getEndDate())))
				{
					ShiftAssignment emp1 = new ShiftAssignment();
				    emp1.setempId(emp.get(i).getempId());
				    emp1.setEndDate(pickupDate.plusMinutes(preptime));
				    emp1.setStartDate(pickupDate);
				    System.out.println("Assignment done to :" +emp.get(i).getempId());	
				    return 1;
			     }
			}
		}
		return 0;
	}
	
}
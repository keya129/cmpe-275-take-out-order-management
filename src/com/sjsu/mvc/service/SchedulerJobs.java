package com.sjsu.mvc.service;

import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
* Entity bean with JPA annotations
* @author Rekha Shankar -  5/14/2016
* 
* Initial development 
*
*/


@EnableScheduling
public class SchedulerJobs {
	
	private OrderService orderService;

	@Autowired(required = true)
	@Qualifier(value = "orderService")
	public void setMenuService(OrderService os) {
		this.orderService = os;
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Scheduled(fixedRate = 60000)
    public void emailNotification()
    {
        System.out.println("Inside Scheduler");
        
       List<String> emails = orderService.getEmailsList();
       
       //Sending Regular Emails for time intervals
       
       if(emails != null){
       if(emails.size() > 0){
    	   for (String email: emails){
    		   System.out.println(email);
    	       
    	        final String emailTo = email+".com";
    		    final String subject = "Take Out Order Alert" ;
    		    final String message = "Hi Sir/Madam,"
    		    		+ "Your order is being processed currently. This is to inform you that food will be ready by the pickuptime mentioned by you."
    		    		+ "Regards,"
    		    		+ "Team Foodie";
               
    		   // for logging
    		    System.out.println("emailTo: " + emailTo);
    		    System.out.println("subject: " + subject);
    		    System.out.println("message: " + message);
    		    

    		    mailSender.send(new MimeMessagePreparator() {

    		    @Override
    		    public void prepare(MimeMessage mimeMessage) throws Exception {
    		    MimeMessageHelper messageHelper = new MimeMessageHelper(
    		    mimeMessage, true, "UTF-8");
    		    messageHelper.setFrom("emailfoodie@gmail.com");
    		    messageHelper.setTo(emailTo);
    		    messageHelper.setSubject(subject);
    		    messageHelper.setText(message);
    		    
    		     
    		    }
    		    });
    	   }
         }
       }
        
    }
	
	@Scheduled(fixedRate = 60000)
    public void orderStatus()
    {
		System.out.println("Inside Order Status");
        
	    orderService.trackOrderStatus();
	       
	       
    }

}
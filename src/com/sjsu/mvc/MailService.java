package com.sjsu.mvc;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class MailService {
	 
	  @SuppressWarnings("unused")
	public  void sendSimpleMail() {
		    // [START simple_example]
		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);

		    try {
		      Message msg = new MimeMessage(session);
		      msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
		      msg.addRecipient(Message.RecipientType.TO,
		                       new InternetAddress("keyabhatt90@gmail.com", "Mr. User"));
		      msg.setSubject("Your account has been activated");
		      msg.setContent("String", "code");
		      Transport.send(msg);
		      System.out.println("Mail has been sent");
		    } catch (AddressException e) {
		      // ...
		    } catch (MessagingException e) {
		      // ...
		    } catch (UnsupportedEncodingException e) {
		      // ...
		    }
		    // [END simple_example]
		  }

}

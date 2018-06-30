package com.ge.tms.config;


import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Nitin K.
 * Service class containing method to send an email.
 */
@Service
public class EmailService {

	 private JavaMailSender javaMailSender;

	    @Autowired
	    public EmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    /**
	     * Method to send an email.
	     * @param toEmail String
	     * @param subject String
	     * @param message1 String
	     * @return void
	     * @author Nitin K.
	     */
	    public void sendMail(String toEmail, String subject, String message1) {
	    	
	    	System.out.println("Inside send Mail method********");
	    	
	    	System.out.println("toEmail----"+toEmail+"subject---"+subject+"message---"+message1);
	    	  SimpleMailMessage mailMessage = new SimpleMailMessage();
	          
	       /*  mailMessage.setTo(toEmail);
	          mailMessage.setSubject(subject);
	          mailMessage.setText(message1);
	          mailMessage.setFrom("MailsBilling@GE.com");
	          javaMailSender.send(mailMessage);*/
	          
	          System.out.println("Success*");
	          
	          try{
	        	  
	              MimeMessage Mimemessage = javaMailSender.createMimeMessage();
	   
	              MimeMessageHelper message = new MimeMessageHelper(Mimemessage, true);
	   
	              message.setFrom("MailsBilling@GE.com");
	              message.setTo(toEmail);
	              message.setSubject(subject);
	              message.setText(message1,true);
	              //message.setText("My alternative text", true);
	              //message.addBcc("BCC email");
	              //message.addCc("CC email");
	   
	             /* FileSystemResource file = new FileSystemResource("E:\\Sameerahmed CV.pdf");
	              message.addAttachment(file.getFilename(), file);*/
	   
	              javaMailSender.send(Mimemessage);    
	              
	              
	             }catch (MessagingException e) {
	                  throw new MailParseException(e);
	             }
	   
	              System.out.println("Mail Sent Successfully With Attachment.....!");
	              
	              
	   
	       
	    }
}

package com.kh.toy.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.kh.toy.common.code.Config;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;

public class MailSender {

	private static final Properties SMTP_PROPERTIES;
	
	static {
		SMTP_PROPERTIES = new Properties();
		SMTP_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
		SMTP_PROPERTIES.put("mail.smtp.ssl.protocols", "TLSv1.2");
		SMTP_PROPERTIES.put("mail.smtp.starttls.enable", "true");
		SMTP_PROPERTIES.put("mail.smtp.port", "587");
		SMTP_PROPERTIES.put("mail.smtp.auth", "true");
	}
	
	
	
	public void sendEmail(String to, String subject, String htmlText) {

		
	    try {
	        MimeMessage msg = new MimeMessage(getSession());
	        msg.setFrom("bargyoon@gmail.com");
	        msg.setRecipients(Message.RecipientType.TO, to);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        msg.setText(htmlText,"utf-8","html");
	        Transport.send(msg);
	    } catch (MessagingException mex) {
	        throw new HandlableException(ErrorCode.MAIL_SENDING_FAIL_ERROR,mex);
	    }
		
	
	}
	
	private Session getSession() {
		  Session session = Session.getDefaultInstance(SMTP_PROPERTIES, new Authenticator() { 
		    	public PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication(Config.SMTP_AUTHENTIFICATION_ID.DESC
		    				, Config.SMTP_AUTHENTIFICATION_PASSWORD.DESC); 
		    		} 
		    	});
		  return session;
	}
	
}

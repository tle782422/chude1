package com.truyentranh.webtruyen.security.test;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.utils.SendMailUtil;


@Service
public class EmailService {

	private static final String username="hienle01112002@gmail.com";
	private static final String password = "gebbfinfabhkkzop";
	
	public static boolean sendEmail(String emailid, String twoFaCode) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailid));
		
		 message.setSubject("Two Factor Authentication code from our Service");
         message.setText("Your Two Factor Authentication code is:"+twoFaCode);
         Transport.send(message);
         return true;
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		sendEmail("tle782422@gmail.com", "ABC");
	}
}
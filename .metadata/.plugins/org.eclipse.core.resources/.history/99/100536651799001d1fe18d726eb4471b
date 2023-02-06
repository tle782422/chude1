package com.truyentranh.webtruyen.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailUtil {

	/** JAVAX MAIL Properties*/
	private static final String PROP_MAIL_ADDRESS = "mail.mailAddress";
	private static final String PROP_MAIL_PASSWORD = "mail.password";
	private static final String PROP_MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String PROP_MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
	private static final String PROP_MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String PROP_MAIL_SMTP_PORT = "mail.smtp.port";

	/**
	 * Send Mail
	 * 
	 * @param receiptMail
	 * @param mailTitle
	 * @param mailContent
	 * @return RetCode
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException 
	 */
	public static int sendMail(String receiptMail, String mailTitle, String mailContent)
			throws MessagingException, UnsupportedEncodingException {


		Properties configFileProperties = readProperties("application.properties");

		String mailAddress = configFileProperties.getProperty(PROP_MAIL_ADDRESS);

		// Get Properties From File properties
		Properties prop = new Properties();
		prop.put(PROP_MAIL_SMTP_AUTH, configFileProperties.getProperty(PROP_MAIL_SMTP_AUTH));
		prop.put(PROP_MAIL_SMTP_STARTTLS, configFileProperties.getProperty(PROP_MAIL_SMTP_STARTTLS));
		prop.put(PROP_MAIL_SMTP_HOST, configFileProperties.getProperty(PROP_MAIL_SMTP_HOST));
		prop.put(PROP_MAIL_SMTP_PORT, configFileProperties.getProperty(PROP_MAIL_SMTP_PORT));

		// Authentication Mail Account
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailAddress, configFileProperties.getProperty(PROP_MAIL_PASSWORD));
			}
		});

		// Send Mail
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailAddress, "demo-noreply@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiptMail));
		message.setHeader("Content-Type","text/html; charset=UTF-8");
		message.setSubject(mailTitle);
		message.setContent(mailContent, "text/html; charset=UTF-8");
		Transport.send(message);

		return 1;
	}

	/**
	 * Read Properties from configure file
	 * 
	 * @param fileName
	 * @return Properties
	 */
	public static Properties readProperties(String fileName) {

		InputStream inputStream = SendMailUtil.class.getClassLoader().getResourceAsStream(fileName);
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
			return prop;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getAlphaNumericString() {
		int n = 6;
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		sendMail("tle782422@gmail.com", "Mail Test", "Mail Content");
		String code = SendMailUtil.getAlphaNumericString();
		System.out.print(code);
	}
}
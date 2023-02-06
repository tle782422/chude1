package com.truyentranh.webtruyen.security.test;

import org.springframework.stereotype.Service;

@Service
public class SMSService {

	private final static String ACCOUNT_SID = "<ACCOUNT_SID>";
	private final static String AUTH_ID = "**********************";
	
//	static {
//		Twilio.init(ACCOUNT_SID, AUTH_ID);
//	}
//	
//	public boolean send2FaCode(String mobilenumber, String twoFaCode) {
//		
//		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("<TWILIO FROM NUMBER>"),
//				"Your Two Factor Authentication code is: "+ twoFaCode).create();
//		
//		return true;
//		
//		
//	}
}

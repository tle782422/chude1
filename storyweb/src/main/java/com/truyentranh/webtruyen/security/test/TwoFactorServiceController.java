package com.truyentranh.webtruyen.security.test;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwoFactorServiceController {

	@Autowired
	EmailService emailService;
	@Autowired
	DAOService daoService;
	@Autowired
	SMSService smsService;
	
	@GetMapping(value="/users/{userid}/emails/{emailid}/2fa") 
	public ResponseEntity<Object> send2faCodeinEmail(@PathVariable("userid") String id, @PathVariable("emailid") String emailid) throws AddressException, MessagingException {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
		emailService.sendEmail(emailid, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/users/{userid}/mobilenumbers/{mobilenumber}/2fa") 
	public ResponseEntity<Object> send2faCodeinSMS(@PathVariable("userid") String id, @PathVariable("mobilenumber") String mobile) {
		String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
//		smsService.send2FaCode(mobile, twoFaCode);
		daoService.update2FAProperties(id, twoFaCode);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/users/{userid}/codes/{two-facode}") 
	public ResponseEntity<Object> verify(@PathVariable("userid") String id, @PathVariable("two-facode") String code) {
	
		boolean isValid = daoService.checkCode(id, code);
		
		if(isValid)
			return new ResponseEntity<>(HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
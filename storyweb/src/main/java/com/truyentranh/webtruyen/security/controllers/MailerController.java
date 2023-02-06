package com.truyentranh.webtruyen.security.controllers;



import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.truyentranh.webtruyen.security.dto.MailInfoDto;
import com.truyentranh.webtruyen.utils.SendMailUtil;

@Controller
@RequestMapping("/mailer")
public class MailerController {



	
	@ModelAttribute("mail")
    public MailInfoDto userRegistrationDto() {
        return new MailInfoDto();
    }
	
	@GetMapping
	public String showForm() {
		return "/security/mailer";
	}	
	
	@PostMapping("/verify")
	public String send(@ModelAttribute("mail") MailInfoDto mail, HttpSession session) {
		try {
			String code = SendMailUtil.getAlphaNumericString();
			SendMailUtil.sendMail(mail.getTo(), mail.getSubject(), code);
			session.setAttribute("code", code);
		} catch(MessagingException e) {
			 e.getMessage();
		} catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
		return "/security/verify";
	}
	@PostMapping("/reset_password")
	public String verify(HttpServletRequest request, HttpSession session) {
		String code = request.getParameter("code");
		String checkverify = (String) session.getAttribute("code");
		if(code.equals(checkverify)){
			session.removeAttribute("code");
				return "accountchange";
		} else {
			session.removeAttribute("code");
				return "redirect:/security/mailer?success";
		}
	}

}

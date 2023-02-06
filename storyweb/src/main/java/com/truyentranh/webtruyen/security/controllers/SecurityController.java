package com.truyentranh.webtruyen.security.controllers;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.truyentranh.webtruyen.security.dto.ResetPasswordDataDto;
import com.truyentranh.webtruyen.security.services.CustomerAccountService;



public class SecurityController {

	public static final String LAST_USERNAME_KEY = "LAST_USERNAME";
	
    @Resource(name = "customerAccountService")
    private CustomerAccountService customerAccountService;
    
    @GetMapping("/login")
    public String loginPage(final Model model, HttpSession session,boolean loginError,boolean invalidSession) {
    	String userName =getUserName(session);
        if(loginError){
            if(StringUtils.isNotEmpty(userName) && customerAccountService.loginDisabled(userName)){
                model.addAttribute("accountLocked", Boolean.TRUE);
                model.addAttribute("forgotPassword", new ResetPasswordDataDto());
                return "security/login";
            }
        }
        if(invalidSession){
            model.addAttribute("invalidSession", "You already have an active session. We do not allow multiple active sessions");
        }
        model.addAttribute("forgotPassword", new ResetPasswordDataDto());
        model.addAttribute("accountLocked", Boolean.FALSE);
        return "security/login";
    }
    
    
    @GetMapping("/logout")
    public ModelAndView fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {        
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
          
        return new ModelAndView("redirect:/security/login?logout");
    }
    
    final String getUserName(HttpSession session){
        final String username = (String) session.getAttribute(LAST_USERNAME_KEY);
        if(StringUtils.isNotEmpty(username)){
            session.removeAttribute(LAST_USERNAME_KEY); // we don't need it and removing it.
        }
        return username;
    }
   
    @RequestMapping("/index")
    public String homePage() {
        return "index";
    }
}

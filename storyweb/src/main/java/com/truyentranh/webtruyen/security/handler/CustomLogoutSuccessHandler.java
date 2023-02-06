package com.truyentranh.webtruyen.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandler extends 
SimpleUrlLogoutSuccessHandler 
{
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
	    return new CustomLogoutSuccessHandler();
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
		Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
		if(request.getParameter("expired") != null)
		{
			response.sendRedirect(request.getContextPath()+"/login?expired=true");
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/login?logout=true");
		}
	}
}
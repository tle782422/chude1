package com.truyentranh.webtruyen.security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.truyentranh.webtruyen.security.dto.UserRegistrationDto;
import com.truyentranh.webtruyen.security.models.User;


public interface UserServiceImpl extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);


}

package com.truyentranh.webtruyen.security.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.truyentranh.webtruyen.security.dto.UserRegistrationDto;
import com.truyentranh.webtruyen.security.services.UserServiceImpl;



@Controller
@RequestMapping("registration")
public class UserRegistrationController {

	private UserServiceImpl userService;

	public UserRegistrationController(UserServiceImpl userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "/security/registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}

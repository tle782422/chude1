package com.truyentranh.webtruyen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ApplicationController {
	
	
	@GetMapping("/index")
	public String goHome() {
		return "index";
	}
	@GetMapping("/home")
	public String goStory() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
	
	
    @GetMapping("hr")
    public String hr(){
        return "/hr/index";
    }

    @GetMapping("products")
    public String fleet(){
        return "/products/index";
    }

    @GetMapping("customers")
    public String accounts(){
        return "/customers/index";
    }

    @GetMapping("reports")
    public String reports(){
        return "/reports/index";
    }
    
    @GetMapping("parameters")
    public String parameters(){
        return "/parameters/index";
    }

    @GetMapping("security")
    public String security(){
        return "/security/index";
    }
	@GetMapping("/404")
	public String error() {
		return "404";
	}
	
	
}

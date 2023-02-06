package net.codejava;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class MainController {
 
    @RequestMapping("")
    public String viewHomePage() {
        return "index";
    }
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
     
    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
     
    @GetMapping("/admin/home")
    public String viewAdminHomePage() {
        return "admin/admin_home";
    }
     
    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user/user_login";
    }
     
    @GetMapping("/user/home")
    public String viewUserHomePage() {
        return "user/user_home";
    }  
}

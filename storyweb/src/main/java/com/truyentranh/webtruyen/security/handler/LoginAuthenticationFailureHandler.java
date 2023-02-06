package com.truyentranh.webtruyen.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.truyentranh.webtruyen.security.models.User;
import com.truyentranh.webtruyen.security.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
    private UserService userService;
	
//	@Value("jdj.security.failedlogin.count")
//	private int MAX_FAILED_ATTEMPTS;
	public static final int MAX_FAILED_ATTEMPTS = 2;
	
    public static final String LAST_USERNAME_KEY = "LAST_USERNAME";

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
//    	String email = request.getParameter("email");
//        User user = userService.getByEmail(email);
//        if (user != null) {
//            if (user.isAccountVerified()==true && user.isLoginDisabled()==false) {
//                if (user.getFailedLoginAttempts() > MAX_FAILED_ATTEMPTS) {
//                    userService.lock(user);
//                    exception = new LockedException("Your account has been locked due to 3 failed attempts."
//                            + " It will be unlocked after 24 hours.");
//                }
//            } else if (user.isLoginDisabled()==true) {
//                if (userService.unlockWhenTimeExpired(user)) {
//                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
//                }
//            }
//             
//        }
        request.getSession().setAttribute(LAST_USERNAME_KEY, request.getParameter("username"));
        super.onAuthenticationFailure(request, response, exception);
    }
}

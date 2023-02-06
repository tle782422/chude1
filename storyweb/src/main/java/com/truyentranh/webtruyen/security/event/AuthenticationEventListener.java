//package com.truyentranh.webtruyen.security.event;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.security.authentication.AuthenticationEventPublisher;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//
//import com.truyentranh.webtruyen.security.bruteforce.BruteForceProtectionService;
//
//public class AuthenticationEventListener implements AuthenticationEventPublisher {
//
//    private static Logger LOG  = LoggerFactory.getLogger(AuthenticationFailureListener.class);
//
//    @Resource(name="bruteForceProtectionService")
//    private BruteForceProtectionService bruteForceProtectionService;
//    
////    @Autowired
////    private HttpServletRequest request;
//    
//    @Autowired
//    private ApplicationEventPublisher applicationEventPublisher;
//
//	
//	@Override
//	public void publishAuthenticationSuccess(Authentication authentication) {
//        String username= authentication.getName().toString();;
//        LOG.info("********* login successful for user {} ", username);
//        bruteForceProtectionService.resetBruteForceCounter(username);
//
//	}
//
//	@Override
//	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
//		
//
//	}
//
//}

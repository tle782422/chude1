package com.truyentranh.webtruyen.security.event;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.truyentranh.webtruyen.security.bruteforce.BruteForceProtectionService;


@Component("authenticationFailureListener")
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static Logger LOG  = LoggerFactory.getLogger(AuthenticationFailureListener.class);

    @Resource(name="bruteForceProtectionService")
    private BruteForceProtectionService bruteForceProtectionService;
    
//    @Autowired
//    private HttpServletRequest request;
    
//    @Autowired
//    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        String username = event.getAuthentication().getName();
        boolean check = bruteForceProtectionService.isBlocked(username);
//      applicationEventPublisher.publishEvent(username);
        if(check==true) {
        	throw new LockedException("fail!");   
        } else {
        	bruteForceProtectionService.registerLoginFailure(username);
        }
    }
    
   
}
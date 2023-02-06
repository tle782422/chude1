package com.truyentranh.webtruyen.security.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

//    @Resource
//    private MFATokenManager mfaTokenManager;

//    @Resource
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }
//
//    protected void additionalAuthenticationChecks(UserDetails userDetails,
//                                                  UsernamePasswordAuthenticationToken authentication)
//            throws AuthenticationException {
//
//        super.additionalAuthenticationChecks(userDetails, authentication);
//
//        //token check
//        CustomWebAuthenticationDetails authenticationDetails = (CustomWebAuthenticationDetails) authentication.getDetails();
//        CustomUser user = (CustomUser) userDetails;
//        String mfaToken = authenticationDetails.getToken();
//        if(!mfaTokenManager.verifyTotp(mfaToken,user.getSecret())){
//            throw new BadCredentialsException(messages.getMessage(
//                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
//                    "Bad credentials"));
//        }
//    }


}


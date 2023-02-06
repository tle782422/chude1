package com.truyentranh.webtruyen;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.truyentranh.webtruyen.security.core.CustomAuthenticationProvider;
import com.truyentranh.webtruyen.security.event.AuthenticationFailureListener;
import com.truyentranh.webtruyen.security.event.AuthenticationSuccessListener;
import com.truyentranh.webtruyen.security.handler.CustomSuccessHandler;
import com.truyentranh.webtruyen.security.handler.LoginAuthenticationFailureHandler;
import com.truyentranh.webtruyen.security.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserService userService;
	
	@Resource
    UserDetailsService userDetailsService;
	
	@Resource
	private DataSource dataSource;
	
	@Resource
    private CustomAuthenticationProvider customAuthenticationProvider;
	

	//No Encoder ex:tle782422@gmail.com
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}	
	//Encoder by BCrypt
	@Bean
	public PasswordEncoder passwordEncoderType2() {
		return new BCryptPasswordEncoder();
	}	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoderType2());
        return auth;
    }
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//multi provider
        auth.authenticationProvider(authenticationProvider())
        	.authenticationProvider(customAuthenticationProvider);
    }
	
	@Bean
	PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
//    @Bean
//    public AuthenticationEventPublisher authenticationEventPublisher
//            (ApplicationEventPublisher applicationEventPublisher) {
//        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
//    }
	@Bean
	public SessionRegistry sessionRegistry() {
	    SessionRegistry sessionRegistry = new SessionRegistryImpl();
	    return sessionRegistry;
	}
	@Bean
	public static ServletListenerRegistrationBean httpSessionEventPublisher() {
	    return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
	}
    @Bean
    public CustomSuccessHandler successHandler(){
        return new CustomSuccessHandler();
    }
    @Bean
    public LoginAuthenticationFailureHandler failureHandler(){
        LoginAuthenticationFailureHandler failureHandler = new LoginAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/login?error=true");
        return failureHandler;
    }
    
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(){
//        return new CustomAccessDeniedHandler();
//    }
    @Bean
    public AccessDecisionManager accessDecisionManager() {
    	List<AccessDecisionVoter<? extends Object>> decisionVoters
    		= Arrays.asList(
    		new AuthenticatedVoter(),
    		new RoleVoter(),
    		new WebExpressionVoter()
    		);
    	return new UnanimousBased(decisionVoters);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Chỉ đường dẫn đã đăng nhập.
		http.authorizeRequests()
		.antMatchers("/registration**","/login/**","/logout/**", "/js/**", "/css/**", "/img/**").permitAll()
		.antMatchers("/home/**","/registration**","/mailer/**","/users/**").permitAll()
		.antMatchers("/security/user/Edit/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/**").access("hasRole('ROLE_ADMIN')")
		// Voter
		.accessDecisionManager(accessDecisionManager())
		.anyRequest().authenticated();
		
		// Cấu hình remember me, thời gian là 2000 giây.   
		http.rememberMe().tokenRepository(persistentTokenRepository())
        .rememberMeCookieDomain("domain")
        .rememberMeCookieName("custom-remember-me-cookie")
        .userDetailsService(this.userService)
        .tokenValiditySeconds(2000)
        .useSecureCookie(true);

        // Setting HTTPS for my account
        http.requiresChannel().antMatchers("/**").requiresSecure();
        	
		// Cấu hình cho Login Form.
		http.authorizeRequests().and()
		.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.failureUrl("/login?error=true")
			.successHandler(successHandler())
			.failureHandler(failureHandler())
		.and()		
		// Cấu hình cho Logout Form.
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true)
		.and()
			.exceptionHandling().accessDeniedPage("/404");
		// Quản lý phiên
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());
	

	}

}

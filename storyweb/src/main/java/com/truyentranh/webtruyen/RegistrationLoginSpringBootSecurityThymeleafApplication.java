package com.truyentranh.webtruyen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.truyentranh.webtruyen.security.controllers.SpringSecurityAuditorAware;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class RegistrationLoginSpringBootSecurityThymeleafApplication {
	
	@Bean
	public AuditorAware<String> auditorAware(){
		return new SpringSecurityAuditorAware();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RegistrationLoginSpringBootSecurityThymeleafApplication.class, args);
	}

}

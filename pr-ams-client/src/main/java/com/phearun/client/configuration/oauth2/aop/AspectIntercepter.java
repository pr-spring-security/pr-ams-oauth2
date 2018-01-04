package com.phearun.client.configuration.oauth2.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.phearun.client.configuration.security.CustomUserDetails;

@Aspect
@Component
public class AspectIntercepter {
	
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	/**
	 * execution(* com.phearun.client.controller.ArticleController.*(..)) //intercept all methods in ArticleController
	 * execution(* com.phearun.client.controller.*.*(..)) // intercept all methods inside controller package
	 */
	@Before("execution(* com.phearun.client.controller.*.*(..))") 
	public void test(){
		
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		String password = user.getPassword();
		
		System.out.println(String.format("AOP-> Adding: username[%s], password[%s]", username, password));
		
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("username", username);
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().set("password", password);
	}
	
}

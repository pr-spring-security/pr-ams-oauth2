package com.phearun.client.configuration.oauth2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfiguration {

    @Autowired
	private OAuth2ClientContext oAuth2ClientContext;
	
	@Bean
	public OAuth2RestTemplate oauthRestTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(baseOAuth2ProtectedResourceDetails(), oAuth2ClientContext);
		return template;
	}
	
	@Bean
	public OAuth2ProtectedResourceDetails baseOAuth2ProtectedResourceDetails() {
		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
		details.setClientId("trusted");
		details.setClientSecret("secret");
		details.setAccessTokenUri("http://localhost:8080/oauth/token");
		details.setScope(Arrays.asList("trust"));
		return details;
	}

}

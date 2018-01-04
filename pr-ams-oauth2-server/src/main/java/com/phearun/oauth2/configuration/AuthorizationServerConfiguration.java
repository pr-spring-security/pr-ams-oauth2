package com.phearun.oauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public TokenStore tokenStore(){
		return new InMemoryTokenStore();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			
	        //TODO: Trusted client: similar to confidential client but also allowed to handle user password
	        .withClient("trusted").secret("secret")
	        .authorities("ROLE_TRUSTED_CLIENT")
	        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
	        .scopes("trust")
	        .redirectUris("http://localhost:8080/client/")
	        .accessTokenValiditySeconds(120)
	        .refreshTokenValiditySeconds(360)
	        .autoApprove(true)
	    ;
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			
	}
	
}

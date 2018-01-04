package com.phearun.client.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ArticleController {
	
	private OAuth2RestTemplate oAuth2RestTemplate;

	public ArticleController(OAuth2RestTemplate oAuth2RestTemplate) {
		this.oAuth2RestTemplate = oAuth2RestTemplate;
	}
	
	@GetMapping("/rest/articles")
	public JsonNode getAllArticles(){
		return oAuth2RestTemplate.getForObject("http://localhost:8080/v1/articles", JsonNode.class);
	}
		
	@GetMapping("/rest/articles/{id}")
	public JsonNode getArticleById(@PathVariable Integer id){
		return oAuth2RestTemplate.getForObject("http://localhost:8080/v1/articles/" + id, JsonNode.class);
	}
		
	@GetMapping("/rest/access_token")
	public OAuth2AccessToken getAccessToken(){
		return oAuth2RestTemplate.getAccessToken();
	}
	
}

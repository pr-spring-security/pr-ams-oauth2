package com.phearun.oauth2.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phearun.model.Article;
import com.phearun.oauth2.repository.ArticleRepository;

@RestController
@RequestMapping("/v1/articles")
public class ArticleResource {
	
	private ArticleRepository articleRepo;

	public ArticleResource(ArticleRepository articleRepo) {
		this.articleRepo = articleRepo;
	}
	
	@RequestMapping
	public List<Article> getArticles(){
		return articleRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Article getArticle(@PathVariable Integer id){
		return articleRepo.findOne(id);
	}
	
	@PostMapping
	public Article createArticle(@RequestBody Article article){
		return articleRepo.save(article);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeArticle(@PathVariable Integer id){
		articleRepo.delete(id);
		return ResponseEntity.ok(true);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateArticle(@PathVariable Integer id, @RequestBody Article article){
		
		if(articleRepo.findOne(id) != null){
			article.setId(id);
			return ResponseEntity.ok(articleRepo.save(article));
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	}
	
}

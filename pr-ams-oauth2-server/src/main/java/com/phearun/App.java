package com.phearun;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phearun.model.Article;
import com.phearun.oauth2.repository.ArticleRepository;

@SpringBootApplication
public class App implements ApplicationRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	private ArticleRepository articleRepo;
	
	public App(ArticleRepository articleRepo) {
		this.articleRepo = articleRepo;
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		Arrays.asList("Why Quitters Can Become Winners Too", "The Lost Art of Criticism", "Is Planning a Kind of Guessing?")
			.forEach(title->{
				articleRepo.save(new Article(title));
			});
	}
}

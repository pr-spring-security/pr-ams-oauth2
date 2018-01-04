package com.phearun.oauth2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phearun.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}

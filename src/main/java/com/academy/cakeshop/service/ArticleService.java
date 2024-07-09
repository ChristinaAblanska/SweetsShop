package com.academy.cakeshop.service;

import com.academy.cakeshop.persistance.entity.Article;
import com.academy.cakeshop.persistance.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article updateArticle(Long id, Article updatedArticle) {
        Optional<Article> existingArticleOptional = articleRepository.findById(id);
        if (existingArticleOptional.isPresent()) {
            updatedArticle.setId(id);
            return articleRepository.save(updatedArticle);
        }
        return null;
    }
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}

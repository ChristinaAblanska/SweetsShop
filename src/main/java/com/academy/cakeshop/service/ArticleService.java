package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.AccountHistoryRequestDTO;
import com.academy.cakeshop.dto.ArticleRequestDTO;
import com.academy.cakeshop.persistance.entity.AccountHistory;
import com.academy.cakeshop.persistance.entity.Article;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.entity.Product;
import com.academy.cakeshop.persistance.repository.AccountHistoryRepository;
import com.academy.cakeshop.persistance.repository.ArticleRepository;
import com.academy.cakeshop.persistance.repository.BankAccountRepository;
import com.academy.cakeshop.persistance.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service

public class ArticleService {
    @Autowired
    private final ArticleRepository articleRepository;
    private final ProductRepository productRepository;


    public Article createArticle(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
        article.setArticleName(articleRequestDTO.getArticleName());
        article.setPrice(articleRequestDTO.getPrice());
        Optional<Product> productOptional = productRepository.findById(articleRequestDTO.getProductId());
        if (productOptional.isPresent()) {
            article.setProduct(productOptional.get());
            return articleRepository.save(article);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article updateArticle(Long id, ArticleRequestDTO articleRequestDTO) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            article.setArticleName(articleRequestDTO.getArticleName());
            article.setPrice(articleRequestDTO.getPrice());
            Optional<Product> productOptional = productRepository.findById(articleRequestDTO.getProductId());
            if (productOptional.isPresent()) {
                article.setProduct(productOptional.get());
                return articleRepository.save(article);
            } else {
                throw new RuntimeException("Product not found");
            }
        } else {
            throw new RuntimeException("Article not found");
        }
    }

    public void deleteArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if (articleOptional.isPresent()) {
            articleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Article not found");
        }
    }
}
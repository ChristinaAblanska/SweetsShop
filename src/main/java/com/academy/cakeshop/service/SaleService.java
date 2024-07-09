package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.SaleRequestDTO;
import com.academy.cakeshop.persistance.entity.Article;
import com.academy.cakeshop.persistance.entity.Sale;
import com.academy.cakeshop.persistance.repository.ArticleRepository;
import com.academy.cakeshop.persistance.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {


    private final SaleRepository saleRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, ArticleRepository articleRepository) {
        this.saleRepository = saleRepository;
        this.articleRepository = articleRepository;
    }


    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    public Sale createSale(SaleRequestDTO saleRequestDTO) {

        Article article = articleRepository.findById(saleRequestDTO.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));


        Sale sale = new Sale();
        sale.setDate(saleRequestDTO.getDate());
        sale.setAmount(saleRequestDTO.getAmount());
        sale.setArticle(article);


        return saleRepository.save(sale);
    }


    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found"));
    }

    public Sale updateSale(Long saleId, SaleRequestDTO saleRequestDTO) {
        Sale existingSale = saleRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found"));
        Article article = articleRepository.findById(saleRequestDTO.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        existingSale.setDate(saleRequestDTO.getDate());
        existingSale.setAmount(saleRequestDTO.getAmount());
        existingSale.setArticle(article);

        return saleRepository.save(existingSale);
    }

    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }

}
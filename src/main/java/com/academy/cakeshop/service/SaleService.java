package com.academy.cakeshop.service;

import com.academy.cakeshop.dto.ArticleRequestDTO;
import com.academy.cakeshop.dto.SaleRequestDTO;
import com.academy.cakeshop.dto.SaleResponseDTO;
import com.academy.cakeshop.persistance.entity.Article;
import com.academy.cakeshop.persistance.entity.Product;
import com.academy.cakeshop.persistance.entity.Sale;
import com.academy.cakeshop.persistance.repository.ArticleRepository;
import com.academy.cakeshop.persistance.repository.SaleRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class SaleService {


    private final SaleRepository saleRepository;
    private final ArticleRepository articleRepository;

    public Sale createSale(SaleRequestDTO saleRequestDTO){
      Sale sale=new Sale();
        sale.setDate(saleRequestDTO.getDate());
        sale.setAmount(saleRequestDTO.getAmount());
        Optional<Article> articleOptional = articleRepository.findById(saleRequestDTO.getArticleId());
        if (articleOptional.isPresent()) {
            sale.setArticle(articleOptional.get());
            return saleRepository.save(sale);
        } else {
            throw new RuntimeException("Sale not found");
        }
    }
    public List<SaleResponseDTO> getSalesByDate(LocalDate date) {
        return saleRepository.findBySaleDate(date);
    }
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }
    public Sale updateSale(Long id, SaleRequestDTO saleRequestDTO) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            sale.setDate(saleRequestDTO.getDate());
            sale.setAmount(saleRequestDTO.getAmount());
            Optional<Article> articleOptional = articleRepository.findById(saleRequestDTO.getArticleId());
            if (articleOptional.isPresent()) {
                sale.setArticle(articleOptional.get());
            } else {
                throw new RuntimeException("Article not found");
            }
            return saleRepository.save(sale);
        } else {
            throw new RuntimeException("Sale not found");
        }
    }

    public void deleteSale(Long id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (saleOptional.isPresent()) {
            saleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Sale not found");
        }
    }
}
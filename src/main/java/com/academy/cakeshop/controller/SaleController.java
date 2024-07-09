package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.SaleRequestDTO;
import com.academy.cakeshop.persistance.entity.Sale;
import com.academy.cakeshop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
public class SaleController {


    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        Sale createdSale = saleService.createSale(saleRequestDTO);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long saleId) {
        Sale sale = saleService.getSaleById(saleId);
        return ResponseEntity.ok(sale);
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long saleId, @RequestBody SaleRequestDTO saleRequestDTO) {
        Sale updatedSale = saleService.updateSale(saleId, saleRequestDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long saleId) {
        saleService.deleteSale(saleId);
        return ResponseEntity.noContent().build();
    }


}
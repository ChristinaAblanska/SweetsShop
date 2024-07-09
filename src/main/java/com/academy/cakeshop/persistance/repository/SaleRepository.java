package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.persistance.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findByDate(LocalDate date);
}

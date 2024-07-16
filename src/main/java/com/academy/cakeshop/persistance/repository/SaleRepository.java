package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.dto.SaleResponseDTO;
import com.academy.cakeshop.persistance.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("select s from Sale s WHERE s.date = date")
    List<SaleResponseDTO> findBySaleDate(LocalDate date);

    @Query("select s from Sale s where s.date = ?1")
    List<Sale> findListOfSalesByDate(LocalDate date);
}

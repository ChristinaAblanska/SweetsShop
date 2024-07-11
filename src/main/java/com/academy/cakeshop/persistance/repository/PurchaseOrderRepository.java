package com.academy.cakeshop.persistance.repository;

import com.academy.cakeshop.dto.PurchaseOrderRequestDTO;
import com.academy.cakeshop.persistance.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("select po from PurchaseOrder po WHERE po.date = date")
    List<PurchaseOrderRequestDTO> findByOrderDate(LocalDate date);
}

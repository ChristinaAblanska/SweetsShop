package com.academy.cakeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderResponseDTO {
    private Long id;
    private Integer quantity;
    private Double price;
    private LocalDate date;
    private String status;
    private Long productId;
    private Long unitId;
    private Long contractId;
}

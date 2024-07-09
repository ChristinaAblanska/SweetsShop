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
    private Integer quantity;
    private Double price;
    private LocalDate date;
    private Long productId;
    private String productName;
    private Long unitId;
    private String unitName;
}

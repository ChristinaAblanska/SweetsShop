package com.academy.cakeshop.dto;

import com.academy.cakeshop.validation.ValidDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderRequestDTO {
    @NotNull(message = "Quantity is mandatory")
    @Positive(message = "Quantity must be a positive value")
    private Integer quantity;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be a positive value")
    private Double price;


    @NotBlank(message = "Date is mandatory")
    private LocalDate date;

    @NotNull(message = "Product ID is mandatory")
    private Long productId;

    @NotNull(message = "Unit ID is mandatory")
    private Long unitId;
}

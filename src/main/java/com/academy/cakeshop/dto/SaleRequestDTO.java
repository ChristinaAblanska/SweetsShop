package com.academy.cakeshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleRequestDTO {
    @NotNull(message = "Date is mandatory")
    private LocalDate date;


    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Article ID is mandatory")
    private Long articleId;
}

package com.academy.cakeshop.dto;

import com.academy.cakeshop.validation.IBAN;
import com.academy.cakeshop.validation.ValidAmount;
import com.academy.cakeshop.validation.ValidCurrency;
import com.academy.cakeshop.validation.ValidDate;
import jakarta.validation.constraints.NotBlank;
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
public class AccountHistoryRequestDTO {

    @NotNull(message = "Date cannot be null")
    private LocalDate date;


    @NotBlank(message = "From account cannot be blank")
    @IBAN(message = "Invalis fromAccount IBAN")
    private String fromAccount;

    @NotBlank(message = "To account cannot be blank")
    @IBAN(message = "Invalis fromAccount IBAN")
    private String toAccount;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Currency cannot be blank")
    private String currency;
}

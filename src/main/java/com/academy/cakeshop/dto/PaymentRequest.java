package com.academy.cakeshop.dto;

import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.persistance.entity.BankAccount;
import com.academy.cakeshop.persistance.entity.Contract;
import com.academy.cakeshop.validation.IBAN;
import com.academy.cakeshop.validation.ValidCurrency;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;

public record PaymentRequest(
        @NotBlank(message = "Required field!")
        @Min(value = 0, message = "No negative values allowed!")
        Long contractID,
        @NotBlank(message = "Required field!")
        @IBAN
        String fromIBAN,
        @NotBlank(message = "Required field!")
        @IBAN
        String toIBAN,
        @NotBlank(message = "Required field!")
        @Min(value = 1, message = "No negative values allowed!")
        Double amount,
        @NotBlank(message = "Required field!")
        @ValidCurrency
        String currency
)
{}
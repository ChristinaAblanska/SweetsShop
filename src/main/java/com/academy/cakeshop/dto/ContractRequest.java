package com.academy.cakeshop.dto;

import com.academy.cakeshop.validation.ValidContractPeriod;
import com.academy.cakeshop.validation.ValidContractStatus;
import com.academy.cakeshop.validation.ValidCurrency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ContractRequest(
        @NotBlank(message = "Required field!")
        @Min(value = 1, message = "Minimal contract sum = 1!")
        Double contractSum,
        @ValidCurrency
        @NotBlank(message = "Required field!")
        String currency,
        @ValidContractPeriod
        @NotBlank(message = "Required field!")
        String contractPeriod,
        @ValidContractStatus
        @NotBlank(message = "Required field!")
        String contractStatus
)
{}
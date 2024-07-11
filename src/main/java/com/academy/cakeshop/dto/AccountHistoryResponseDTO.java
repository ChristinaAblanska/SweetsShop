package com.academy.cakeshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AccountHistoryResponseDTO {
    private Long id;
    private LocalDate date;
    private String fromAccountId;
    private String toAccountId;
    private double amount;
    private String currency;

}

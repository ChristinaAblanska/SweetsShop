package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.AccountHistoryRequestDTO;
import com.academy.cakeshop.persistance.entity.AccountHistory;
import com.academy.cakeshop.service.AccountHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-history")
public class AccountHistoryController {

    @Autowired
    private AccountHistoryService accountHistoryService;

    @PostMapping
    public ResponseEntity<?> createAccountHistory(@Valid @RequestBody AccountHistoryRequestDTO requestDTO) {
        AccountHistory accountHistory = accountHistoryService.createAccountHistory(requestDTO);
        return ResponseEntity.ok(accountHistory);
    }
}

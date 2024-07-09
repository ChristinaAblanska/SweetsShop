package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.BankAccountRequest;
import com.academy.cakeshop.dto.BankAccountResponse;
import com.academy.cakeshop.enumeration.Currency;
import com.academy.cakeshop.service.BankAccountService;
import com.academy.cakeshop.validation.IBAN;
import com.academy.cakeshop.validation.ValidCurrency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bankAccounts")
@Validated
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> getAll() {
        List<BankAccountResponse> bankAccountResponseList = bankAccountService.getAll();
        return new ResponseEntity<>(bankAccountResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> getById(@NotNull(message = "Required field!")
                                                       @Min(value = 1, message = "No negative values allowed")
                                                       @PathVariable
                                                       Long id) {
        BankAccountResponse bankAccountResponse = bankAccountService.getByID(id);
        return new ResponseEntity<>(bankAccountResponse, HttpStatus.OK);
    }



//    @GetMapping({"/iban"})
//    public ResponseEntity<BankAccountResponse> getByUserId(@NotBlank(message = "Required field!")
//                                                           @Min(value = 1, message = "No negative values allowed")
//                                                           @RequestParam(name = "userId")
//                                                           Long userId) {
//        BankAccountResponse bankAccountResponse = bankAccountService.getByUserID(userId);
//        return new ResponseEntity<>(bankAccountResponse, HttpStatus.OK);
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(Principal principal, @RequestBody BankAccountRequest bankAccountRequest) {
        bankAccountService.create(bankAccountRequest, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@NotNull(message = "Required field!")
                                        @IBAN
                                        @RequestParam String iban) {
        bankAccountService.deleteByID(iban);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.ContractRequest;
import com.academy.cakeshop.dto.ContractResponse;
import com.academy.cakeshop.dto.PaymentResponse;
import com.academy.cakeshop.service.ContractService;
import com.academy.cakeshop.service.PaymentService;
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
@RequestMapping("/api/v1/contracts")
@Validated
public class ContractController {
    private final ContractService contractService;
    private final PaymentService paymentService;

    public ContractController(ContractService contractService, PaymentService paymentService) {
        this.contractService = contractService;
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<ContractResponse> getById(@NotNull(message = "Required field!")
                                                    @Min(value = 1, message = "No negative values allowed")
                                                    @RequestParam(name = "contractId")
                                                    Long id) {
        ContractResponse contractResponse = contractService.getByID(id);
        return new ResponseEntity<>(contractResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(Principal principal, @RequestBody ContractRequest contractRequest) {
        String userName = principal.getName();
        contractService.create(contractRequest, userName);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{contractId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateContractStatus(@RequestBody ContractRequest contractRequest,
                                                  @NotNull(message = "Required field!")
                                                  @Min(value = 1, message = "No negative values allowed!") Long contractIdId) {
        int updatedRows = contractService.updateContractStatus(contractRequest.contractStatus(), contractIdId);
        if (updatedRows > 0) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable @NotNull(message = "Required field!")
                                        @Min(value = 1, message = "No negative values allowed!") Long id) {
        contractService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/payments/{contractId}")
    public ResponseEntity<List<PaymentResponse>> getAllPaymentsByContractId(@NotNull(message = "Required field!")
                                                                            @Min(value = 1, message = "No negative values allowed!")
                                                                            @PathVariable Long contractId) {
        List<PaymentResponse> paymentResponseList = paymentService.getAllByContractID(contractId);
        return new ResponseEntity<>(paymentResponseList, HttpStatus.OK);

    }
}
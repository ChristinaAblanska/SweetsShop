package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.PaymentRequest;
import com.academy.cakeshop.dto.PaymentResponse;
import com.academy.cakeshop.dto.RentDTO;
import com.academy.cakeshop.persistance.repository.PaymentRepository;
import com.academy.cakeshop.service.PaymentService;
import com.academy.cakeshop.service.RentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    private final RentService rentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@NotBlank(message = "Required field!")
                                                       @Min(value = 1, message = "No negative values allowed!")
                                                       @PathVariable Long id) {
        PaymentResponse paymentResponse = paymentService.getByID(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentService.create(paymentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value= "/rent",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> payRent(
            @Valid @RequestBody RentDTO rentDTO
    ) {
        try {
            rentService.payRent(rentDTO);
            return ResponseEntity.ok("Rent paid successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error paying rent: " + e.getMessage());
        }
    }

    @PostMapping("/income")
    @PreAuthorize("hasRole='STORE'")
    public ResponseEntity<String> distributeIncome(
            @RequestParam("saleDate") LocalDate saleDate,
            @RequestParam("rentAmount") double rentAmount) {
        try {
            rentService.distributeIncome(saleDate, rentAmount);
            return ResponseEntity.ok("Income distributed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error distributing income: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentById(@NotBlank(message = "Required field!")
                                                   @Min(value = 1, message = "No negative values allowed!")
                                                   @PathVariable Long id) {
        paymentService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
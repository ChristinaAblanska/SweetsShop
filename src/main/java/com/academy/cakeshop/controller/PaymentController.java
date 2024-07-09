package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.PaymentRequest;
import com.academy.cakeshop.dto.PaymentResponse;
import com.academy.cakeshop.persistance.repository.PaymentRepository;
import com.academy.cakeshop.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentById(@NotBlank(message = "Required field!")
                                                   @Min(value = 1, message = "No negative values allowed!")
                                                   @PathVariable Long id) {
        paymentService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
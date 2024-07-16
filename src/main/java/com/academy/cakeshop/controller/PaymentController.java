package com.academy.cakeshop.controller;

import com.academy.cakeshop.dto.PaymentResponse;
import com.academy.cakeshop.service.PaymentService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STORE')")
    public ResponseEntity<PaymentResponse> getById(@NotBlank(message = "Required field!")
                                                       @Min(value = 1, message = "No negative values allowed!")
                                                       @PathVariable Long id) {
        PaymentResponse paymentResponse = paymentService.getByID(id);
        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    //TODO - test
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('STORE')")
//    public ResponseEntity<?> createPayment(Principal principal,  @Valid @RequestBody PaymentRequest paymentRequest) {
//        paymentService.create(paymentRequest, principal.getName());
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePaymentById(@NotBlank(message = "Required field!")
                                                   @Min(value = 1, message = "No negative values allowed!")
                                                   @PathVariable Long id) {
        paymentService.deleteByID(id);
        return "Payment successfully deleted!\n Плащането беше успешно изтрито!";
    }
}
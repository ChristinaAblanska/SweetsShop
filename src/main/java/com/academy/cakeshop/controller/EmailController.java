package com.academy.cakeshop.controller;

import com.academy.cakeshop.mail.EmailService;
import com.academy.cakeshop.mail.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emailService/send")
public class EmailController {
    private final EmailService emailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendMail(@RequestBody EmailDto emailDto) {
        emailService.sendSimpleEmail(emailDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

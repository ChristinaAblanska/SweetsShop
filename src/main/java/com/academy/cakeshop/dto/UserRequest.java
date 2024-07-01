package com.academy.cakeshop.dto;

import com.academy.cakeshop.validation.ValidPassword;
import com.academy.cakeshop.validation.ValidPhoneNumber;
import com.academy.cakeshop.validation.ValidRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRequest(
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String firstName,
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String lastName,
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String userName,
        @ValidPassword
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String password,
        @Email
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String email,
        @ValidPhoneNumber
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String phoneNumber,
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String address,
        @ValidRole
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String role
)
{}
package com.academy.cakeshop.dto;

import com.academy.cakeshop.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserUpdateDTO(
        @NotBlank(message = "Required field!")
        @Min(value = 1, message = "No negative values allowed!")
        Long id,
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String firstName,
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String lastName,
        @Email
        @NotBlank(message = "Required field!")
        @Length(min = 1, max = 255, message = "Incorrect field length!")
        String email
) {
}
package com.academy.cakeshop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("/(\\+)?(359|0)8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}/");
    }
}
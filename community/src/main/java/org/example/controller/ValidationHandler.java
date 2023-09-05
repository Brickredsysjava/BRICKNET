package org.example.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidationHandler {

    private final Validator validator;

    public ValidationHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateDto(Object dto, Errors errors) {
        validator.validate(dto, errors);
    }

    public void validateAllDtos(Object... dtos) {
        for (Object dto : dtos) {
            Errors errors = new BeanPropertyBindingResult(dto, dto.getClass().getName());
            validateDto(dto, errors);
            if (errors.hasErrors()) {
                handleValidationErrors(dto, errors);
            }
        }
    }

    private void handleValidationErrors(Object dto, Errors errors) {
        // Log validation errors
        System.out.println("Validation errors for " + dto.getClass().getSimpleName() + ":");
        errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));

        // You can also throw a custom exception here if needed
        // For example, you could create a custom ValidationException and throw it
        // throw new ValidationException("Validation errors occurred.");
    }
}


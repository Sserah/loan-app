package com.example.RegistrationModule.model.dto;

import org.springframework.http.HttpStatus;

public record CustomerRegistrationServiceResponseDto(HttpStatus status, String message) {
}

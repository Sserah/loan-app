package com.example.RegistrationModule.interfaces;

import com.example.RegistrationModule.model.CustomerDto;
import com.example.RegistrationModule.model.dto.CustomerRegistrationServiceResponseDto;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    CompletableFuture<ResponseEntity<CustomerRegistrationServiceResponseDto>> registerNewCustomer(
            CustomerDto customerDto);
}

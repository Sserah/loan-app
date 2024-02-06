package com.example.RegistrationModule.controller;

import com.example.RegistrationModule.commons.Constants;
import com.example.RegistrationModule.interfaces.RegistrationService;

import com.example.RegistrationModule.model.CustomerDto;
import com.example.RegistrationModule.model.dto.CustomerRegistrationServiceResponseDto;
import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = Constants.REGISTRATION_BASE_URL)
@RestController
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/application")
    public ResponseEntity<CustomerRegistrationServiceResponseDto> registerCustomerForLoan(@Valid @RequestBody CustomerDto customerDto)
            throws ExecutionException, InterruptedException {
        logger.info("registerCustomerForLoan() invoked.");
       return registrationService.registerNewCustomer(customerDto).get();
    }
}

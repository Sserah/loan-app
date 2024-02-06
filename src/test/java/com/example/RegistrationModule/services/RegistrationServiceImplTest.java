package com.example.RegistrationModule.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.RegistrationModule.model.CustomerDto;
import com.example.RegistrationModule.model.dto.CustomerRegistrationServiceResponseDto;
import com.example.RegistrationModule.repository.RegistrationRepository;
import com.example.RegistrationModule.validator.RegistrationValidatorImpl;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RegistrationServiceImplTest {
    @Mock
    RegistrationRepository registrationRepository;

    @Mock
    RegistrationValidatorImpl registrationValidator;

    @InjectMocks
    RegistrationServiceImpl registrationService;

    CustomerDto customerDto = null;

    CustomerRegistrationServiceResponseDto responseDto = null;

    @BeforeEach
    void setUp() {
        customerDto = new CustomerDto("abraham","lugonzo",23,'M',4323323);
    }

    @Nested
    class registerNewCustomer{
        @Test
        void should_return_200_Success_if_registerdSuccessfully() throws ExecutionException, InterruptedException {
            responseDto = new CustomerRegistrationServiceResponseDto(HttpStatus.OK,"User successfully registered");
            when(registrationRepository.countCustomerByIdNumber(customerDto.idNumber())).thenReturn(0);
            when(registrationValidator.isEligibleForLoan(customerDto.age())).thenReturn(true);

            assertEquals(HttpStatus.OK,registrationService.registerNewCustomer(customerDto).get().getStatusCode());
            assertEquals(responseDto,registrationService.registerNewCustomer(customerDto).get().getBody());
        }

        @Test
        void should_return_403_Forbidden_if_not_allowed_to_register() throws ExecutionException, InterruptedException {
            responseDto = new CustomerRegistrationServiceResponseDto(HttpStatus.OK,"User successfully registered");
            when(registrationRepository.countCustomerByIdNumber(customerDto.idNumber())).thenReturn(0);
            when(registrationValidator.isEligibleForLoan(10)).thenReturn(true);

            assertEquals(HttpStatus.FORBIDDEN,registrationService.registerNewCustomer(customerDto).get().getStatusCode());
        }

        //todo test other cases scenario as well
    }




}
package com.example.RegistrationModule.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RegistrationValidatorImplTest {

    @InjectMocks
    RegistrationValidatorImpl registrationValidator;


    @BeforeEach
    void setUp() {
    }

    @Test
    void isEligibleForLoan_shouldAllow_userToRegister_when_above_18years() {
        int age = 23;
        assertTrue(registrationValidator.isEligibleForLoan(age));
    }
}
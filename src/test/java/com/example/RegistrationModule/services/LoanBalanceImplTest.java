package com.example.RegistrationModule.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.RegistrationModule.repository.BalanceRepository;
import com.example.RegistrationModule.repository.RegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LoanBalanceImplTest {

    @Mock
    BalanceRepository balanceRepository;
    @Mock
    RegistrationRepository registrationRepository;

    @InjectMocks
    LoanBalanceImpl loanBalanceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkBalance() {
        double balance = 45_900.00;

        when(registrationRepository.countCustomerByIdNumber(123)).thenReturn(1);
        when(balanceRepository.findBalanceInfoByIdNumber(123)).thenReturn(balance);
        assertEquals(balance,loanBalanceImpl.checkBalance(123));
    }
}
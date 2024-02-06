package com.example.RegistrationModule.controller;

import com.example.RegistrationModule.commons.Constants;
import com.example.RegistrationModule.services.LoanBalanceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = Constants.REGISTRATION_BASE_URL)
@RestController
public class LoanBalanceController {

    private final LoanBalanceImpl loanBalance;

    @Autowired
    public LoanBalanceController(LoanBalanceImpl loanBalance) {
        this.loanBalance = loanBalance;
    }

    @GetMapping("/balance/{idNumber}")
    public ResponseEntity<Double> checkBalance(@RequestParam(required = true) Integer idNumber){
        double response = loanBalance.checkBalance(idNumber);
       return  ResponseEntity.ok(response);
    }
}

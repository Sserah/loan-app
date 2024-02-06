package com.example.RegistrationModule.services;

import com.example.RegistrationModule.interfaces.Balance;
import com.example.RegistrationModule.repository.BalanceRepository;
import com.example.RegistrationModule.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBalanceImpl implements Balance {
    private  final RegistrationRepository registrationRepository;
    private final BalanceRepository balanceRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoanBalanceImpl.class);


    @Autowired
    public LoanBalanceImpl(RegistrationRepository registrationRepository, BalanceRepository balanceRepository) {
        this.registrationRepository = registrationRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public double checkBalance(Integer idNumber) {
        logger.info("checkBalance() invoked.");
        if(registrationRepository.countCustomerByIdNumber(idNumber) <= 0){
            logger.info("User with id {} does not exists...");

            //todo here return response from. user not exists dto
            return 0.0;
        }
        double balance = balanceRepository.findBalanceInfoByIdNumber(idNumber);
        logger.info("Balance is {} ...",balance);
        return balance; //hardcoded val but needs to get data from db
    }
}

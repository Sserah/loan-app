package com.example.RegistrationModule.repository;

import com.example.RegistrationModule.model.BalanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceInfo,Integer> {
    double findBalanceInfoByIdNumber(Integer id);
}

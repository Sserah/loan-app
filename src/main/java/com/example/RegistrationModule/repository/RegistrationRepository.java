package com.example.RegistrationModule.repository;

import com.example.RegistrationModule.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Customer,Integer> {
    int countCustomerByIdNumber(Integer idNumber);
}

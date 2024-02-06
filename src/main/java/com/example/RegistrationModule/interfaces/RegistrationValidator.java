package com.example.RegistrationModule.interfaces;

import com.example.RegistrationModule.model.enums.CrbStatus;

public interface RegistrationValidator {

    boolean isEligibleForLoan(Integer age);

    CrbStatus validateCrbStatus(Integer idNumber);
}

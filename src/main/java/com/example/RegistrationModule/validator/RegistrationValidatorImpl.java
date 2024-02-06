package com.example.RegistrationModule.validator;

import com.example.RegistrationModule.commons.Constants;
import com.example.RegistrationModule.interfaces.RegistrationValidator;
import com.example.RegistrationModule.model.enums.CrbStatus;
import org.springframework.stereotype.Component;

@Component
public class RegistrationValidatorImpl implements RegistrationValidator {


    /** This service checks if customer has reached the age to apply for a loan
     *
     * @param age
     * @return
     */
    @Override
    public boolean isEligibleForLoan(Integer age) {
        return age > Constants.UNDER_AGE;
    }

    @Override
    public CrbStatus validateCrbStatus(Integer idNumber) {

        // todo assumed that he is eligible
        return CrbStatus.ELIGIBLE;
    }
}

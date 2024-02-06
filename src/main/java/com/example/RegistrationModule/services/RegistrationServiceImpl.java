package com.example.RegistrationModule.services;

import com.example.RegistrationModule.interfaces.RegistrationService;
import com.example.RegistrationModule.model.Customer;
import com.example.RegistrationModule.model.CustomerDto;
import com.example.RegistrationModule.model.dto.CustomerRegistrationServiceResponseDto;
import com.example.RegistrationModule.model.enums.CrbStatus;
import com.example.RegistrationModule.repository.RegistrationRepository;
import com.example.RegistrationModule.validator.RegistrationValidatorImpl;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

@Configuration
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final RegistrationValidatorImpl registrationValidator;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationValidatorImpl registrationValidator,
                                   RegistrationRepository registrationRepository) {
        this.registrationValidator = registrationValidator;
        this.registrationRepository = registrationRepository;
    }

    /** This function is used for registering a new customer
     *
     * @param customerDto
     * @return CustomerRegistrationServiceResponseDto
     */
    @Async
    public CompletableFuture<ResponseEntity<CustomerRegistrationServiceResponseDto>> registerNewCustomer(CustomerDto customerDto){

        CustomerRegistrationServiceResponseDto response = null;
        try {
            logger.info("Request to apply for a loan requested...");
            Customer newCustomer = new Customer();
            BeanUtils.copyProperties(customerDto,newCustomer);

            boolean isEligibleToApplyForLoan = registrationValidator.isEligibleForLoan(newCustomer.getAge());
            if(!isEligibleToApplyForLoan){
                logger.info("Customer cannot apply for a loan since of age restrictions...");
                response = new CustomerRegistrationServiceResponseDto(HttpStatus.FORBIDDEN,"Application is valid for 18 years and above");
                return CompletableFuture.completedFuture(new ResponseEntity<CustomerRegistrationServiceResponseDto>(
                        response, HttpStatus.FORBIDDEN));
            }

            if(registrationRepository.countCustomerByIdNumber(customerDto.idNumber()) > 0){
                logger.info("Customer already exists..");
                response = new CustomerRegistrationServiceResponseDto(HttpStatus.FORBIDDEN,"User already exists");
                return CompletableFuture.completedFuture(new ResponseEntity<CustomerRegistrationServiceResponseDto>(response, HttpStatus.FORBIDDEN));
            }

            if(registrationValidator.validateCrbStatus(newCustomer.getIdNumber()) != CrbStatus.ELIGIBLE){
                logger.info("Customer has a bad CRB status");
                response = new CustomerRegistrationServiceResponseDto(HttpStatus.FORBIDDEN,"Customer has a bad CRB status");
                return CompletableFuture.completedFuture(new ResponseEntity<CustomerRegistrationServiceResponseDto>(response, HttpStatus.FORBIDDEN));
            }

            registrationRepository.save(newCustomer);
            logger.info("sucessfully registered a new customer");

            response = new CustomerRegistrationServiceResponseDto(HttpStatus.OK,"User successfully registered");
            return CompletableFuture.completedFuture(new ResponseEntity<CustomerRegistrationServiceResponseDto>(response, HttpStatus.OK));
        }catch (Exception ex){
            logger.warn("An exception has occurred while registering a new user... EX -> {}",ex.getMessage());
            response = new CustomerRegistrationServiceResponseDto(HttpStatus.FORBIDDEN,"User already exists");
            return CompletableFuture.completedFuture(new ResponseEntity<CustomerRegistrationServiceResponseDto>(response, HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }
}

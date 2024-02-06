package com.example.RegistrationModule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence",sequenceName = "customer_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_sequence")
    private Integer id;
    @Column(name = "first_name", columnDefinition = "CHARACTER VARYING(40)", updatable = true)
    private String firstName;
    @Column(name = "last_name", columnDefinition = "CHARACTER VARYING(40)", updatable = true)
    private String lastName;
    @Column
    @NonNull
    private Integer age;

    @NotBlank
    @Column(name = "gender", columnDefinition = "CHARACTER VARYING(1)", updatable = true)
    private Character gender;
    @Column(unique = true)
    @NonNull
    private Integer idNumber;
    @Transient
    private boolean isEligibleToGetALoan = false;

}

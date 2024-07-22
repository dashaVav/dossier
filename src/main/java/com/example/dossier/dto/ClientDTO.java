package com.example.dossier.dto;

import com.example.dossier.dto.enums.Gender;
import com.example.dossier.dto.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private Passport passport;
    private EmploymentDTO employment;
    private String account;
}

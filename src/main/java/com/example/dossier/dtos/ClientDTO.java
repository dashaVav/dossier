package com.example.dossier.dtos;

import com.example.dossier.dtos.enums.Gender;
import com.example.dossier.dtos.enums.MaritalStatus;
import com.example.dossier.dtos.helpers.EmploymentDTO;
import com.example.dossier.dtos.helpers.Passport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

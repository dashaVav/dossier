package com.example.dossier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
}

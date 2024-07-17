package com.example.dossier.dtos.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
}

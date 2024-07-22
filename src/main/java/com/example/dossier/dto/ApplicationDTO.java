package com.example.dossier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Long applicationId;
    private LocalDateTime creationDate;
    private LoanOfferDTO appliedOffer;
    private ClientDTO client;
    private CreditDto credit;
}

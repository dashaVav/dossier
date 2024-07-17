package com.example.dossier.dtos;

import com.example.dossier.dtos.enums.ApplicationStatus;
import com.example.dossier.dtos.helpers.ApplicationStatusHistory;
import com.example.dossier.dtos.helpers.LoanOfferDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Long applicationId;
    private ApplicationStatus applicationStatus;
    private LocalDateTime creationDate;
    private LoanOfferDTO appliedOffer;
    private LocalDateTime signDate;
    private String sesCode;
    private List<ApplicationStatusHistory> statusHistory;
    private ClientDTO clientDTO;
    private CreditDto creditDto;
}

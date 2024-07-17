package com.example.dossier.dtos.helpers;

import com.example.dossier.dtos.enums.StatusHistory;
import com.example.dossier.dtos.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusHistory {
    private ApplicationStatus applicationStatus;
    private LocalDateTime time;
    private StatusHistory changeType;
}
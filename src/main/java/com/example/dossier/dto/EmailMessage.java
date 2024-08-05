package com.example.dossier.dto;

import com.example.dossier.dto.enums.EmailMessageStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    @NotBlank
    private String address;

    @NotNull
    private EmailMessageStatus theme;

    @NotNull
    private Long applicationId;
}

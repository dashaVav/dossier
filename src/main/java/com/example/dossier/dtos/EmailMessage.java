package com.example.dossier.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    private String address;
    private EmailMessageStatus theme;
    private Long applicationId;
}

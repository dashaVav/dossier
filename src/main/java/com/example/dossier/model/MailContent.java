package com.example.dossier.model;

import jakarta.activation.DataSource;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MailContent {
    private String subject;
    private String text;
    private DataSource file;
}

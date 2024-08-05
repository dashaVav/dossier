package com.example.dossier.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DossierError {
    private String error;
    private Integer status;
}

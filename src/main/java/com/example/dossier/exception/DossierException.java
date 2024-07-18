package com.example.dossier.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DossierException {
    private String error;
    private Integer status;
}

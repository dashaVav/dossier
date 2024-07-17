package com.example.dossier.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DossierException {
    private String error;
    private Integer status;
}

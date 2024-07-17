package com.example.dossier.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DealException extends RuntimeException {
    private String error;
    private Integer status;

    public DealException(String error, Integer status) {
        super(error);
        this.error = error;
        this.status = status;
    }
}

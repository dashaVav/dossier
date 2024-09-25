package com.example.dossier.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DealException extends RuntimeException {
    private final String error;
    private final Integer status;

    public DealException(String error, Integer status) {
        super(error);
        this.error = error;
        this.status = status;
    }
}

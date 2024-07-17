package com.example.dossier.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<DossierException> handleTheException(RuntimeException e, HttpStatus status) {
        return new ResponseEntity<>(
                new DossierException(e.getMessage(), status.value()),
                status
        );
    }

    @ExceptionHandler(DealException.class)
    public ResponseEntity<DossierException> handleDealException(DealException e) {
        return handleTheException(e, HttpStatus.resolve(e.getStatus()));
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<DossierException> handleConnectException(ConnectException e) {
        return handleTheException(new RuntimeException("Connection refused."), HttpStatus.SERVICE_UNAVAILABLE);
    }
}

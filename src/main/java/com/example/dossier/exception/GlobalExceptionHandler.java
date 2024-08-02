package com.example.dossier.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<DossierException> handleTheException(RuntimeException e, HttpStatus status) {
        log.error("Exception: {} handled normally. Message: {}", e.getClass().getName(), e.getMessage());
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
        return handleTheException(new RuntimeException("Service Unavailable"), HttpStatus.SERVICE_UNAVAILABLE);
    }
}

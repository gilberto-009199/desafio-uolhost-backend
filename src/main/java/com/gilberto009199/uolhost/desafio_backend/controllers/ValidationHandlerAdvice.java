package com.gilberto009199.uolhost.desafio_backend.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandlerAdvice {

    public ResponseEntity<?> resolveHandler(RuntimeException ex) {
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Map.of("message", ex.getMessage()));
    }
}

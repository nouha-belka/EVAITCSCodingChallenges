package com.evaitcs.productapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * ============================================================================
 * CLASS: GlobalExceptionHandler
 * TOPIC: @ControllerAdvice — Centralized exception handling
 * ============================================================================
 *
 * @ControllerAdvice applies exception handling to ALL controllers.
 * Instead of try/catch in every controller, handle errors in ONE place.
 *
 * INTERVIEW TIP:
 * "I use @ControllerAdvice for global exception handling. It centralizes
 *  error responses, ensures consistent error formats, and keeps controllers clean."
 * ============================================================================
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * TODO 1: Handle validation errors (400 Bad Request)
     * When @Valid fails, Spring throws MethodArgumentNotValidException.
     * Extract field errors and return a structured error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        // Map<String, Object> errors = new HashMap<>();
        // errors.put("timestamp", LocalDateTime.now());
        // errors.put("status", 400);
        // Map<String, String> fieldErrors = new HashMap<>();
        // ex.getBindingResult().getFieldErrors().forEach(error ->
        //     fieldErrors.put(error.getField(), error.getDefaultMessage()));
        // errors.put("errors", fieldErrors);
        // return ResponseEntity.badRequest().body(errors);

        return ResponseEntity.badRequest().build(); // Replace
    }

    /**
     * TODO 2: Handle resource not found (404)
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NoSuchElementException ex) {
        // TODO: Return structured 404 error response
        return ResponseEntity.notFound().build();
    }

    /**
     * TODO 3: Handle all other unexpected exceptions (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        // TODO: Return structured 500 error response with message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


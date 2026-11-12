package com.company.emailtemplate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Email sending failure
    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<Map<String, Object>> handleEmailSendingException(
            EmailSendingException exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("error", "Email Delivery Failed");
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 502);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

    // Validation failure
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, Object> response = new HashMap<>();

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("error", "Validation Failed");
        response.put("messages", errors);
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);

        return ResponseEntity.badRequest().body(response);
    }

    // General runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("error", "Bad Request");
        response.put("message", exception.getMessage());
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 400);

        return ResponseEntity.badRequest().body(response);
    }
}
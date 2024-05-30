package com.test.indivara.test_indivara.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.test.indivara.test_indivara.dto.response.WebResponseError;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<WebResponseError<Object>> responseStatusException(HttpMessageNotReadableException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponseError.builder().status("Error").errors(
                        "Malformed JSON Request")
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<WebResponseError<Object>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponseError.builder().status("Error").errors(
                        "Bad Request")
                        .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<WebResponseError<Object>> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(WebResponseError.builder().status("Error").errors(
                        exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponseError<Object>> responseStatusException(ResponseStatusException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponseError.builder().status("Error").errors(exception.getReason()).build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<WebResponseError<Object>> badCredentialsException(BadCredentialsException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(WebResponseError.builder().status("Error").errors(exception.getMessage()).build());
    }
    
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<WebResponseError<Object>> noResourceFoundException(NoResourceFoundException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponseError.builder().status("Error").errors("No Resource Found").build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WebResponseError<Object>> methodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponseError.builder().status("Error").errors(errors).build());
    }
}

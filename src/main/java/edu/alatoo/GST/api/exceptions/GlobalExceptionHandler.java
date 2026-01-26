package edu.alatoo.GST.api.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.builder()
                        .status(404)
                        .message(ex.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.builder()
                        .status(400)
                        .message(ex.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiError.builder()
                        .status(401)
                        .message(ex.getMessage())
                        .timestamp(Instant.now())   
                        .build());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbidden(ForbiddenException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiError.builder()
                        .status(403)
                        .message(ex.getMessage())
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations()
            .stream()
            .map(v -> v.getPropertyPath() + ": " + v.getMessage())
            .findFirst()
            .orElse("Validation error");

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiError.builder()
                    .status(400)
                    .message(message)
                    .timestamp(Instant.now())
                    .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiError.builder()
                    .status(400)
                    .message(message)
                    .timestamp(Instant.now())
                    .build());
    }

}

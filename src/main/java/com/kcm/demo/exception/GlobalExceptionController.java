package com.kcm.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //예외 처리 클래스
public class GlobalExceptionController {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handledIncorrectPasswordException(IncorrectPasswordException x) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(x.getMessage());
    }

    @ExceptionHandler(IncorrectEventException.class)
    public ResponseEntity<String> handledIncorrectEventException(IncorrectEventException x) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(x.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

package com.kcm.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //예외 처리 클래스
public class GlobalExceptionController {

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handledIncorrectPasswordException(IncorrectPasswordException x) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(x.getMessage());
    }

    @ExceptionHandler(IncorrectEventException.class)
    public ResponseEntity<String> handledIncorrectEventException(IncorrectEventException x){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(x.getMessage());
    }

}

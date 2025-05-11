package org.example.rest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<?> handleValidationError() {
        return null;
    }
}

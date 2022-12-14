package com.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
//        List<FieldError> fieldError = e.get
        return new ResponseEntity<>(e.getExceptionCode().getMessage(), HttpStatus.BAD_REQUEST);
    }
}

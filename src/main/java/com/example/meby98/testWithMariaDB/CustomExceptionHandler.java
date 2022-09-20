package com.example.meby98.testWithMariaDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> notValidArguments(MethodArgumentNotValidException e) {
        return new ResponseEntity<>("Arguments not valids", HttpStatus.BAD_REQUEST);
    }
}
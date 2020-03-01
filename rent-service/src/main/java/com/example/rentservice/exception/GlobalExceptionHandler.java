package com.example.rentservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseOnException> serviceExceptionHandler(ServiceException exception) {
        ResponseOnException errorResponse =
                new ResponseOnException(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<ResponseOnException>(errorResponse, exception.getStatus());
    }
}

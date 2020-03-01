package com.example.rentservice.exception;

import org.springframework.http.HttpStatus;

public class FeignCustomException extends ServiceException {
    public FeignCustomException(String message, HttpStatus status) {
        super(message, status);
    }
}

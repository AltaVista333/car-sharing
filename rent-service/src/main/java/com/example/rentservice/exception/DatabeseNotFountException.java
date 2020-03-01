package com.example.rentservice.exception;

import org.springframework.http.HttpStatus;

public class DatabeseNotFountException extends ServiceException {

    public DatabeseNotFountException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}

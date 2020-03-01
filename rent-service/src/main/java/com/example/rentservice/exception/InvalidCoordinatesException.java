package com.example.rentservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidCoordinatesException extends ServiceException {

    public InvalidCoordinatesException(String message, HttpStatus status) {
        super(message, status);
    }
}


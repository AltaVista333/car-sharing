package com.example.rentservice.exception;

public class InvalidRentIdException extends RuntimeException {
    public InvalidRentIdException(String message) {
        super(message);
    }
}

package com.example.rentservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private HttpStatus status;


    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    @Override
    public String getMessage() {
        return String.format("Exception message: %s, http status code: %s", super.getMessage(),
                status.toString());
    }
}

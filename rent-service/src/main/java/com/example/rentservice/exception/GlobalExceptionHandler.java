package com.example.rentservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseOnException> serviceExceptionHandler(ServiceException exception) {
        ResponseOnException errorResponse =
                new ResponseOnException(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<ResponseOnException>(errorResponse, exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseOnException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseOnException errorResponse =
                new ResponseOnException(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<ResponseOnException>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<List<HttpMessageNotReadableException>>(HttpStatus.BAD_REQUEST);
    }
}

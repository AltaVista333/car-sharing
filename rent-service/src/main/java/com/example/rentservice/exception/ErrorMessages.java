package com.example.rentservice.exception;

public interface ErrorMessages {
    String ENDED_RENT = "This rent already ended";
    String NOT_FOUND = "No such element in DataBase";
    String INVALID_PLACE = "Invalid place for rent close";
    String SERVICE_UNAVALIBLE = "Service unavailable.";
    String BAD_CAR = "You can not rent this car";
    String DUPLICATE_RENT = "You already rented car";
}

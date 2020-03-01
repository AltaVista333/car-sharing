package com.example.rentservice.service;

import com.example.rentservice.dto.CarDto;

import java.math.BigDecimal;

public interface CarService {
    CarDto car(Long carId);
    BigDecimal price(Long carId);
}

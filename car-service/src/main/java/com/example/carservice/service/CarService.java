package com.example.carservice.service;

import com.example.carservice.entity.Car;

import java.util.List;

public interface CarService {
    Car addCar(Car car);

    List<Car> getAllCars();

    Car findById(Long id);

    Car updateCar(Car car, Long id);


}

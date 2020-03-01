package com.example.rentservice.service.impl;

import com.example.rentservice.client.CarFeign;
import com.example.rentservice.dto.CarDto;
import com.example.rentservice.service.CarService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarServiceImpl implements CarService {
    private CarFeign carFeign;

    public CarServiceImpl(CarFeign carFeign) {
        this.carFeign = carFeign;
    }

    @Override
    public CarDto car(Long carId) {
        CarDto carDto = carFeign.getCarById(carId);
        return carDto;
    }

    public BigDecimal price(Long carId){
        CarDto car = car(carId);
        return car.getMinutePrice();
    }


}

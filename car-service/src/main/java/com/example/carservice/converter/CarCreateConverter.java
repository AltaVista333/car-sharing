package com.example.carservice.converter;

import com.example.carservice.dto.CarCreateDto;
import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class CarCreateConverter implements GenericConverter<Car, CarCreateDto> {

    @Override
    public Car toEntity(CarCreateDto dto) {
        return Car.builder()
                .model(dto.getModel())
                .carStatus(CarStatus.INACTIVE)
                .adress(dto.getAdress())
                .build();
    }

    @Override
    public CarCreateDto toDto(Car entity) {
        return null;
    }


}

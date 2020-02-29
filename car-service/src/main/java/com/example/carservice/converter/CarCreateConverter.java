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
                .coordX(dto.getCoordX())
                .coordY(dto.getCoordY())
                .minutePrice(dto.getMinutePrice())
                .build();
    }

    @Override
    public CarCreateDto toDto(Car entity) {
        return null;
    }


}

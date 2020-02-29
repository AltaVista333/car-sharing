package com.example.carservice.converter;

import com.example.carservice.dto.CarDto;
import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class CarConverter implements GenericConverter<Car, CarDto> {
    @Override
    public Car toEntity(CarDto dto) {
        return Car.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .carStatus(CarStatus.valueOf(dto.getCarStatus()))
                .coordX(dto.getCoordX())
                .coordY(dto.getCoordY())
                .minutePrice(dto.getMinutePrice())
                .build();
    }

    @Override
    public CarDto toDto(Car entity) {
        return CarDto.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .carStatus(entity.getCarStatus().name())
                .coordX(entity.getCoordX())
                .coordY(entity.getCoordY())
                .minutePrice(entity.getMinutePrice())
                .build();
    }
}

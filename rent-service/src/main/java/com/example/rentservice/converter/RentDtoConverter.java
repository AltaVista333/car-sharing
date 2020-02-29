package com.example.rentservice.converter;

import com.example.rentservice.dto.RentDto;
import com.example.rentservice.entity.Rent;
import com.example.rentservice.entity.RentStatus;
import org.springframework.stereotype.Component;

@Component
public class RentDtoConverter implements GenericConverter<Rent, RentDto> {
    @Override
    public Rent toEntity(RentDto dto) {
        return Rent.builder()
                .id(dto.getId())
                .status(RentStatus.valueOf(dto.getStatus()))
                .userId(dto.getUserId())
                .carId(dto.getCarId())
                .startRent(dto.getStartRent())
                .endRent(dto.getEndRent())
                .build();
    }

    @Override
    public RentDto toDto(Rent entity) {
        return RentDto.builder()
                .id(entity.getId())
                .status(entity.getStatus().name())
                .userId(entity.getUserId())
                .carId(entity.getCarId())
                .startRent(entity.getStartRent())
                .endRent(entity.getEndRent())
                .build();
    }
}

package com.example.rentservice.converter;

import com.example.rentservice.entity.Rent;
import com.example.rentservice.dto.RentCreateDto;
import com.example.rentservice.entity.RentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RentCreateConverter implements GenericConverter<Rent, RentCreateDto> {

    @Override
    public Rent toEntity(RentCreateDto dto) {
        return Rent.builder()
                .userId(dto.getUserId())
                .carId(dto.getCarId())
                .status(RentStatus.VALIDATE)
                .dateTime(LocalDateTime.now())
                .uuid(UUID.randomUUID())
                .build();
    }

    @Override
    public RentCreateDto toDto(Rent entity) {
        return null;
    }
}

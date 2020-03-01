package com.example.clientservice.converter;

import com.example.clientservice.dto.BillDto;
import com.example.clientservice.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillConverter implements GenericConverter<Bill, BillDto> {
    @Override
    public Bill toEntity(BillDto dto) {
        return Bill.builder()
                .amount(dto.getAmount())
                .rentId(dto.getRentId())
                .paidOff(dto.getPaidOff())
                .build();
    }

    @Override
    public BillDto toDto(Bill entity) {
        return BillDto.builder()
                .amount(entity.getAmount())
                .rentId(entity.getRentId())
                .paidOff(entity.getPaidOff())
                .build();
    }
}

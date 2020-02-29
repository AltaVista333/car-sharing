package com.example.rentservice.controller;

import com.example.rentservice.client.CarFeign;
import com.example.rentservice.client.ClientFeign;
import com.example.rentservice.converter.GenericConverter;
import com.example.rentservice.dto.RentCloseDto;
import com.example.rentservice.dto.RentCreateDto;
import com.example.rentservice.dto.RentDto;
import com.example.rentservice.entity.Rent;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.rentservice.logging.RentLogs.CLOSE_RENT;
import static com.example.rentservice.logging.RentLogs.CREATE_RENT;

@RestController
@RequestMapping("rent")
@Slf4j
public class RentController {


    private RentService rentService;
    private GenericConverter<Rent, RentDto> rentDtoGenericConverter;
    private GenericConverter<Rent, RentCreateDto> rentCreateDtoGenericConverter;

    public RentController(CarFeign carFeign, ClientFeign clientFeign, RentService rentService, GenericConverter<Rent, RentDto> rentDtoGenericConverter, GenericConverter<Rent, RentCreateDto> rentCreateDtoGenericConverter) {
        this.rentService = rentService;
        this.rentDtoGenericConverter = rentDtoGenericConverter;
        this.rentCreateDtoGenericConverter = rentCreateDtoGenericConverter;
    }

    @PostMapping(path = "/rentCar")
    public RentDto rentCar(@RequestBody @Valid RentCreateDto createDto){
        log.info(CREATE_RENT, createDto.getCarId(), createDto.getUserId());
        Rent rent = rentCreateDtoGenericConverter.toEntity(createDto);
        Rent newRent = rentService.createRent(rent);
        return rentDtoGenericConverter.toDto(newRent);
    }

    @PostMapping(path = "/endRentCar")
    public RentDto endCarRent(@RequestBody @Valid RentCloseDto closeDto){
        log.info(CLOSE_RENT, closeDto.getId());
        Rent rent = rentService.closeRent(closeDto.getId());
        return rentDtoGenericConverter.toDto(rent);
    }



}

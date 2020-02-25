package com.example.rentservice.controller;

import com.example.rentservice.converter.RentCreateConverter;
import com.example.rentservice.dto.RentCreateDto;
import com.example.rentservice.entity.Rent;
import com.example.rentservice.message.Message;
import com.example.rentservice.producer.ProducerService;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.UUID;

import static com.example.rentservice.logging.RentLogs.CREATE_RENT;

@RestController
@RequestMapping("rent")
@Slf4j
public class RentController {

    private RentService rentService;
    private RentCreateConverter createConverter;

    public RentController(RentService rentService, RentCreateConverter createConverter) {
        this.rentService = rentService;
        this.createConverter = createConverter;
    }

    @PostMapping(path = "/rentCar")
    public UUID rentCar(@RequestBody @Valid RentCreateDto createDto){
        log.info(CREATE_RENT, createDto);
        Rent rent = createConverter.toEntity(createDto);
        rentService.createRent(rent);
        return rent.getUuid();

    }


}

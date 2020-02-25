package com.example.rentservice.service.impl;

import com.example.rentservice.entity.Rent;
import com.example.rentservice.message.CheckCarStatus;
import com.example.rentservice.producer.ProducerService;
import com.example.rentservice.repository.RentRepository;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RentServiceImpl implements RentService {

    private RentRepository rentRepository;
    private ProducerService producerService;

    public RentServiceImpl(RentRepository rentRepository, ProducerService producerService) {
        this.rentRepository = rentRepository;
        this.producerService = producerService;
    }

    @Override
    public UUID createRent(Rent rent) {
        rentRepository.save(rent);
        Rent byUuid = rentRepository.findByUuid(rent.getUuid());
        log.info("res: {}", byUuid.getId());
        producerService.sendToFanoutExchangeToCarService(new CheckCarStatus(byUuid.getUuid(), byUuid.getCarId()));
        return byUuid.getUuid();
    }
}

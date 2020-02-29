package com.example.rentservice.service.impl;

import com.example.rentservice.dto.CarDto;
import com.example.rentservice.dto.ClientDto;
import com.example.rentservice.entity.Rent;
import com.example.rentservice.entity.RentStatus;
import com.example.rentservice.exception.InvalidCoordinatesException;
import com.example.rentservice.exception.InvalidRentIdException;
import com.example.rentservice.message.UpdateCarStatus;
import com.example.rentservice.producer.ProducerService;
import com.example.rentservice.repository.RentRepository;
import com.example.rentservice.service.CarService;
import com.example.rentservice.service.ClientService;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.rentservice.constant.RentRadius.RENT_RADIUS;

@Component
@Slf4j
public class RentServiceImpl implements RentService {

    private CarService carService;
    private ClientService clientService;
    private RentRepository rentRepository;
    private ProducerService producerService;

    public RentServiceImpl(CarService carService, ClientService clientService,
                           RentRepository rentRepository, ProducerService producerService) {
        this.carService = carService;
        this.clientService = clientService;
        this.rentRepository = rentRepository;
        this.producerService = producerService;
    }

    @Override
    @Transactional
    public Rent createRent(Rent rent) {
        Rent savedRent = rentRepository.save(rent);
        log.info("res: {}. status: {}", savedRent.getId(), savedRent.getStatus());
        CarDto car = null;
        ClientDto client = null;
        try {
            car = carService.car(savedRent.getCarId());
            client = clientService.client(savedRent.getCarId());
        } catch (Exception e) {
            log.info("EXCEPTION");
            savedRent.setStatus(RentStatus.REJECTED);
            rentRepository.save(savedRent);
            return savedRent;
        }
        log.info(car.getCarStatus());
        log.info(client.getStatus());
        if (!"ACTIVE".equals(car.getCarStatus().toUpperCase()) || checkOngoingClientRent(client.getId())) {
            log.info("U can't rent this car");
            savedRent.setStatus(RentStatus.REJECTED);
            rentRepository.save(savedRent);
            return savedRent;
        } else {
            producerService.sendToFanoutExchangeToCarService(new UpdateCarStatus(car.getId(),
                    car.getCarStatus(), savedRent.getId()));
            return savedRent;

        }
    }




    @Override
    public Optional<Rent> findRentById(Long id) {
        return rentRepository.findById(id);
    }

    @Override
    public Rent updateRent(Rent rent) {
        return rentRepository.findById(rent.getId())
                .map(updatedRent -> {
                    updatedRent.setStatus(rent.getStatus());
                    Rent save = rentRepository.save(updatedRent);
                    return updatedRent;
                })
                .orElseThrow(() -> new InvalidRentIdException("Invalid id :" + rent.getId()));
    }

    @Override
    public Rent closeRent(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new InvalidRentIdException("Invalid id :" + id));
        CarDto car = carService.car(rent.getCarId());
        Integer coordX = car.getCoordX();
        Integer coordY = car.getCoordY();
        log.info("Car coordinates. X: {}, Y: {}", coordX, coordY);
        if (checkCoordinates(coordX, coordY, RENT_RADIUS)) {
            throw new InvalidCoordinatesException("Invalid place for rent close");
        }
        producerService.sendToFanoutExchangeToCarService(
                new UpdateCarStatus(car.getId(), "ACTIVE", rent.getId()));

        return rent;
    }

    @Override
    public boolean checkOngoingClientRent(Long id) {
        return rentRepository.existClientActiveRent(id) > 0;
    }

    private boolean checkCoordinates(Integer x, Integer y, Integer radius) {
        return x > radius || y > radius;
    }

}

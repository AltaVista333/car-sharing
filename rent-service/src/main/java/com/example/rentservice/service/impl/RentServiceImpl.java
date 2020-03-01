package com.example.rentservice.service.impl;

import com.example.rentservice.dto.CarDto;
import com.example.rentservice.dto.ClientDto;
import com.example.rentservice.entity.Rent;
import com.example.rentservice.entity.RentStatus;
import com.example.rentservice.exception.*;
import com.example.rentservice.message.UpdateCarStatus;
import com.example.rentservice.producer.ProducerService;
import com.example.rentservice.repository.RentRepository;
import com.example.rentservice.service.CarService;
import com.example.rentservice.service.ClientService;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            client = clientService.client(savedRent.getUserId());
        } catch (FeignCustomException e) {
            savedRent.setStatus(RentStatus.REJECTED);
            rentRepository.save(savedRent);
            throw new ServiceException(ErrorMessages.SERVICE_UNAVALIBLE, HttpStatus.SERVICE_UNAVAILABLE);
        }
        if (!"ACTIVE".equals(car.getCarStatus().toUpperCase())) {
            savedRent.setStatus(RentStatus.REJECTED);
            rentRepository.save(savedRent);
            throw new ServiceException(ErrorMessages.BAD_CAR, HttpStatus.BAD_REQUEST);

        } else if (checkOngoingClientRent(client.getId())) {
            savedRent.setStatus(RentStatus.REJECTED);
            rentRepository.save(savedRent);
            throw new ServiceException(ErrorMessages.DUPLICATE_RENT, HttpStatus.BAD_REQUEST);
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
                .orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND ));
    }

    @Override
    public Rent closeRent(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
        if(rent.getStatus().equals(RentStatus.ENDED)){
            throw new ServiceException(ErrorMessages.ENDED_RENT,HttpStatus.CONFLICT );
        }
        CarDto car = carService.car(rent.getCarId());
        Integer coordX = car.getCoordX();
        Integer coordY = car.getCoordY();
        log.info("Car coordinates. X: {}, Y: {}", coordX, coordY);
        if (checkCoordinates(coordX, coordY, RENT_RADIUS)) {
            throw new InvalidCoordinatesException(ErrorMessages.INVALID_PLACE, HttpStatus.CONFLICT);
        }
        producerService.sendToFanoutExchangeToCarService(
                new UpdateCarStatus(car.getId(), "ACTIVE", rent.getId()));

        return rent;
    }

    public BigDecimal calculateBillForClient(Long carId, Long rentId){

        try {
            BigDecimal price = carService.price(carId);
            return rentRepository.findById(rentId)
                    .map(rent -> {
                        LocalDateTime startRent = rent.getStartRent();
                        LocalDateTime endRent = rent.getEndRent();
                        LocalDateTime fromTemp = LocalDateTime.from(startRent);
                        long minutes = fromTemp.until(endRent, ChronoUnit.MINUTES);
                        return BigDecimal.valueOf(minutes).multiply(price);
                    }).orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
        } catch (FeignCustomException e) {
            throw new ServiceException(ErrorMessages.SERVICE_UNAVALIBLE, HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @Override
    public boolean checkOngoingClientRent(Long id) {
        return rentRepository.existClientActiveRent(id) > 0;
    }

    private boolean checkCoordinates(Integer x, Integer y, Integer radius) {
        return x > radius || y > radius;
    }



}

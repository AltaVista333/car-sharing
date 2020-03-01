package com.example.carservice.service.impl;

import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import com.example.carservice.exception.DatabeseNotFountException;
import com.example.carservice.exception.ErrorMessages;
import com.example.carservice.message.CarRentStatus;
import com.example.carservice.message.UpdateCarStatus;
import com.example.carservice.producer.ProducerService;
import com.example.carservice.repository.CarRepository;
import com.example.carservice.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private ProducerService producerService;

    public CarServiceImpl(CarRepository repository, ProducerService producerService) {
        this.carRepository = repository;
        this.producerService = producerService;
    }

    @Override
    @Transactional
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    @Transactional
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
    }

    @Override
    @Transactional
    public Car updateCar(Car car, Long id) {
        return carRepository.findById(id)
                .map(updatedCar -> {
                    updatedCar.setId(car.getId());
                    updatedCar.setCarStatus(car.getCarStatus());
                    updatedCar.setCoordX(car.getCoordX());
                    updatedCar.setCoordY(car.getCoordY());
                    updatedCar.setModel(car.getModel());
                    updatedCar.setMinutePrice(car.getMinutePrice());
                    return carRepository.save(updatedCar);
                })
                .orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
    }

    @Override
    @Transactional
    public void handleRentServiceMessage(UpdateCarStatus carStatusMessage) {
        Car car = findById(carStatusMessage.getCarId());

        if (car.getCarStatus().equals(CarStatus.ACTIVE)) {
            car.setCarStatus(CarStatus.RENTED);
            updateCar(car, car.getId());
            producerService.sendToFanoutExchange(
                    new CarRentStatus(CarStatus.RENTED.name(), car.getId(), carStatusMessage.getRentId()));
        } else if (car.getCarStatus().equals(CarStatus.RENTED)) {
            car.setCarStatus(CarStatus.ACTIVE);
            updateCar(car, car.getId());
            producerService.sendToFanoutExchange(
                    new CarRentStatus(CarStatus.ACTIVE.name(), car.getId(), carStatusMessage.getRentId()));
        }


    }

    @Override
    @Transactional
    public List<Car> findCarsWithActiveStatus() {
        return carRepository.findAllByCarStatus(CarStatus.ACTIVE);
    }

    @Override
    @Transactional
    public Car moveCar(Integer coordX, Integer coordY, Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
        car.setCoordX(coordX);
        car.setCoordY(coordY);
        return carRepository.save(car);

    }

    @Override
    @Transactional
    public Car updateCarStatusToActive(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
        car.setCarStatus(CarStatus.ACTIVE);
        return carRepository.save(car);
    }
}

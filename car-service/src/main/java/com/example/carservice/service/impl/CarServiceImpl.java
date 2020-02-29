package com.example.carservice.service.impl;

import com.example.carservice.entity.Car;
import com.example.carservice.repository.CarRepository;
import com.example.carservice.service.CarService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Car addCar(Car car) {
        return repository.save(car);
    }

    @Transactional
    public List<Car> getAllCars() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Car findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ServiceException("Car not found"));
    }

    @Override
    @Transactional
    public Car updateCar(Car car, Long id) {
        return repository.findById(id)
                .map(updatedCar -> {
                    updatedCar.setId(car.getId());
                    updatedCar.setCarStatus(car.getCarStatus());
                    updatedCar.setCoordX(car.getCoordX());
                    updatedCar.setCoordY(car.getCoordY());
                    updatedCar.setModel(car.getModel());
                    updatedCar.setMinutePrice(car.getMinutePrice());
                    return repository.save(updatedCar);
                })
                .orElseThrow(() -> new ServiceException("No car found"));
    }


}

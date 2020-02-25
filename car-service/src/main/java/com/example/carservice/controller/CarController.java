package com.example.carservice.controller;

import com.example.carservice.converter.GenericConverter;
import com.example.carservice.dto.CarCreateDto;
import com.example.carservice.dto.CarDto;
import com.example.carservice.entity.Car;
import com.example.carservice.message.Message;
import com.example.carservice.producer.ProducerService;
import com.example.carservice.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.carservice.logger.CarControllerLog.*;

@RestController
@RequestMapping("cars")
@Slf4j
public class CarController {

    private CarService carService;
    private GenericConverter<Car, CarDto> carConverter;
    private GenericConverter<Car, CarCreateDto> carCreateConverter;

    public CarController(CarService carService, GenericConverter<Car, CarDto> carConverter, GenericConverter<Car, CarCreateDto> carCreateConverter, ProducerService service) {
        this.carService = carService;
        this.carConverter = carConverter;
        this.carCreateConverter = carCreateConverter;
    }

    @GetMapping
    public List<CarDto> findCars() {
        log.info(ALL_CARS);
        return carConverter.toDto(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<Void> addCar(@RequestBody @Valid CarCreateDto carDto) {
        log.info(CREATE_NEW_CAR, carDto);
        Car car = carCreateConverter.toEntity(carDto);
        carService.addCar(car);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        log.info(CAR_BY_ID, id);
        return carConverter.toDto(carService
                .findById(id));
    }

    @PutMapping("/{id}")
    public CarDto updateCarById(@RequestBody @Valid CarDto carDto, @PathVariable("id") Long id) {
        return carConverter.toDto(
                carService.updateCar(
                        carConverter.toEntity(carDto), id));
    }

}

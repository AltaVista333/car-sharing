package com.example.carservice.controller;

import com.example.carservice.converter.GenericConverter;
import com.example.carservice.dto.CarCreateDto;
import com.example.carservice.dto.CarDto;
import com.example.carservice.dto.CarMoveDto;
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
import javax.validation.constraints.Positive;
import java.util.List;

import static com.example.carservice.logger.CarControllerLog.*;

@RestController
@Slf4j
@RequestMapping("car")
public class CarController {

    private CarService carService;
    private GenericConverter<Car, CarDto> carConverter;
    private GenericConverter<Car, CarCreateDto> carCreateConverter;

    public CarController(CarService carService, GenericConverter<Car, CarDto> carConverter, GenericConverter<Car, CarCreateDto> carCreateConverter, ProducerService service) {
        this.carService = carService;
        this.carConverter = carConverter;
        this.carCreateConverter = carCreateConverter;
    }

    @GetMapping(path = "/all")
    public List<CarDto> findCars() {
        log.info(ALL_CARS);
        return carConverter.toDto(carService.getAllCars());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Void> addCar(@RequestBody @Valid CarCreateDto carDto) {
        log.info(CREATE_NEW_CAR, carDto);
        Car car = carCreateConverter.toEntity(carDto);
        carService.addCar(car);
        return ResponseEntity.status(201).build();
    }

    @GetMapping(path = "/active")
    public List<CarDto> findCarsWithActiveStatus(){
       return carConverter.toDto(carService.findCarsWithActiveStatus());
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        log.info(CAR_BY_ID, id);
        return carConverter.toDto(
                carService.findById(id));
    }

    @PutMapping("/move/{id}")
    public CarDto moveCar(@RequestParam("id") Long carId, @RequestBody CarMoveDto carMoveDto){
        Car car = carService.moveCar(carMoveDto.getCoordX(), carMoveDto.getCoortY(), carId);
        return carConverter.toDto(car);
    }


    @PutMapping("/{id}")
    public CarDto updateCar(@RequestBody @Valid CarDto carDto) {
        return carConverter.toDto(
                carService.updateCar(
                        carConverter.toEntity(carDto), carDto.getId()));
    }

    @PutMapping("/activate/{id}")
    public CarDto setCarStatusToActive(@RequestParam("id") Long carId){
        return carConverter.toDto(
                carService.updateCarStatusToActive(carId));
    }


}

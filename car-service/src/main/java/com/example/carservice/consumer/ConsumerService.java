package com.example.carservice.consumer;

import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import com.example.carservice.message.CarRentStatus;
import com.example.carservice.message.UpdateCarStatus;
import com.example.carservice.producer.ProducerService;
import com.example.carservice.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ConsumerService {

    private CarService carService;
    private ProducerService producerService;

    public ConsumerService(CarService carService, ProducerService producerService) {
        this.carService = carService;
        this.producerService = producerService;
    }

    @RabbitListener(queues = "${fromRent}")
    @Transactional
    public void handleQueueAMessageReception(UpdateCarStatus carStatusMessage) {
        log.info("From rent to car carId: {}. Car status {}",
                carStatusMessage.getCarId(), carStatusMessage.getStatus() );
        Car car = carService.findById(carStatusMessage.getCarId());
        if(car.getCarStatus().equals(CarStatus.ACTIVE)){
            car.setCarStatus(CarStatus.RENTED);
            carService.updateCar(car, car.getId());
            producerService.sendToFanoutExchange(
                    new CarRentStatus(CarStatus.RENTED.name(), car.getId(),carStatusMessage.getRentId()));
            log.info("Car status update: {}", car.getCarStatus());
        } else if(car.getCarStatus().equals(CarStatus.RENTED)) {
            car.setCarStatus(CarStatus.ACTIVE);
            carService.updateCar(car, car.getId());
            producerService.sendToFanoutExchange(
                    new CarRentStatus(CarStatus.ACTIVE.name(), car.getId(), carStatusMessage.getRentId()));
            log.info("Car status update: {}", car.getCarStatus());
        } else {
            log.info("Car status not update: {}", car.getCarStatus());
            producerService.sendToFanoutExchange(
                    new CarRentStatus("bad",car.getId(), carStatusMessage.getRentId()));
        }

    }
}

package com.example.carservice.consumer;

import com.example.carservice.entity.Car;
import com.example.carservice.entity.CarStatus;
import com.example.carservice.message.CarStatusChanged;
import com.example.carservice.message.Message;
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
    public void handleQueueAMessageReception(Message message) {
        log.info("From rent to car : " + message.getName());
        log.info("Car status: {}" , message.getName());
        if("CarStatus".equals(message.getName())){
            log.info("Car id : {}", message.getCarId());
            Car car = carService.findById(message.getCarId());
            log.info(car.getModel());
            if (car.getCarStatus().equals(CarStatus.ACTIVE)){
                car.setCarStatus(CarStatus.LOCKED);
                carService.updateCar(car, car.getId());
                producerService.sendToFanoutExchange(new CarStatusChanged(message.getUuid(), car.getId(), "CarSuccesfulyLocked"));
                log.info("Car status update: {}", car.getCarStatus());
            } else {
                log.info("Bad try");
                producerService.sendToFanoutExchange(new CarStatusChanged(message.getUuid(), car.getId(), "BadTry"));
            }

        } else {
            log.info("Bad try2");
            producerService.sendToFanoutExchange(new CarStatusChanged(message.getUuid(), message.getCarId(), "BadTry"));
        }

    }
}

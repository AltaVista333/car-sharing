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
        carService.handleRentServiceMessage(carStatusMessage);


    }
}

package com.example.rentservice.consumer;

import com.example.rentservice.message.CarRentStatus;
import com.example.rentservice.message.Message;
import com.example.rentservice.producer.ProducerService;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ConsumerService {

    private RentService rentService;
    private ProducerService producerService;


    public ConsumerService(RentService rentService, ProducerService producerService) {
        this.rentService = rentService;
        this.producerService = producerService;
    }

    @RabbitListener(queues = "${fromCar}")
    @Transactional
    public void handleQueueAMessageReception2(CarRentStatus carStatus) {
        rentService.handleCarQueue(carStatus);
    }

    @RabbitListener(queues = "${fromClient}")
    public void handleQueueAMessageReception(Message message) {
        log.info("Message received from Client-service: " + message.getName());


    }
}
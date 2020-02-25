package com.example.rentservice.consumer;

import com.example.rentservice.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {


    @RabbitListener(queues = "${fromCar}")
    public void handleQueueAMessageReception2(Message message) {
        log.info("Message received from Car-service: " + message.getName());

    }

    @RabbitListener(queues = "${fromClient}")
    public void handleQueueAMessageReception(Message message) {
        log.info("Message received from Client-service: " + message.getName());

    }
}
package com.example.rentservice.producer;

import com.example.rentservice.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    @Value("${exchange.toCar}")
    private String fanoutExchangeToCar;

    @Value("${exchange.toClient}")
    private String fanoutExchangeToClient;

    @Autowired
    RabbitTemplate template;

    @Override
    public void sendToFanoutExchangeToCarService(Message message) {
        log.info("send message to car-service: {}", message);
        template.convertAndSend(fanoutExchangeToCar, "", message);
    }

    @Override
    public void sendToFanoutExchangeToClientService(Message message) {
        log.info("send message to client-service: {}", message);
        template.convertAndSend(fanoutExchangeToClient, "", message);
    }
}

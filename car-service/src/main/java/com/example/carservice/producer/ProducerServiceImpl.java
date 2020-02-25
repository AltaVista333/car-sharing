package com.example.carservice.producer;

import com.example.carservice.message.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${exchange.toRent}")
    private String fanoutExchange;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendToFanoutExchange(Message message) {
        rabbitTemplate.convertAndSend(fanoutExchange, "", message);
    }
}

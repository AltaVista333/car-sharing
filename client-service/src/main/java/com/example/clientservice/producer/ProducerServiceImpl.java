package com.example.clientservice.producer;

import com.example.clientservice.message.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${exchange.fromClient}")
    private String fanoutExchange;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendToFanoutExchange(Message message) {
        rabbitTemplate.convertAndSend(fanoutExchange, "", message);
    }

}

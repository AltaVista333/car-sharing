package com.example.clientservice.consumer;

import com.example.clientservice.message.Message;
import com.example.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    ClientService clientService;

    public ConsumerService(ClientService clientService) {
        this.clientService = clientService;
    }

    @RabbitListener(queues = "${toClient}")
    public void handleQueueAMessageReception(Message message) {
        log.info(message.getStatus());
    }
}

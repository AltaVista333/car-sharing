package com.example.rentservice.producer;

import com.example.rentservice.message.BillDto;
import com.example.rentservice.message.BillMessage;
import com.example.rentservice.message.Message;
import com.example.rentservice.message.UpdateCarStatus;
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
    public void sendToFanoutExchangeToCarService(UpdateCarStatus updateCarStatus) {
        log.info("send message to car-service: {}", updateCarStatus);
        template.convertAndSend(fanoutExchangeToCar, "", updateCarStatus);
    }

    @Override
    public void sendToFanoutExchangeToClientService(BillMessage billMessage) {
        log.info("Send bill to client service: {}", billMessage);
        template.convertAndSend(fanoutExchangeToClient, "", billMessage);
    }
}

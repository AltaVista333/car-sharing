package com.example.carservice.producer;


import com.example.carservice.message.Message;

public interface ProducerService {
    void sendToFanoutExchange(Message message);

}

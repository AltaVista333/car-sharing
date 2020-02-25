package com.example.clientservice.producer;


import com.example.clientservice.message.Message;

public interface ProducerService {
    void sendToFanoutExchange(Message message);

}

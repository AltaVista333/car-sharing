package com.example.rentservice.producer;

import com.example.rentservice.message.Message;

public interface ProducerService {
    void sendToFanoutExchangeToCarService(Message message);
    void sendToFanoutExchangeToClientService(Message message);


}

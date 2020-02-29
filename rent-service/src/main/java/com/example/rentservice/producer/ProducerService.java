package com.example.rentservice.producer;

import com.example.rentservice.message.Message;
import com.example.rentservice.message.UpdateCarStatus;

public interface ProducerService {
    void sendToFanoutExchangeToCarService(UpdateCarStatus updateCarStatus);
    void sendToFanoutExchangeToClientService(Message message);


}

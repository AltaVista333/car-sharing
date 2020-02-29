package com.example.carservice.producer;


import com.example.carservice.message.CarRentStatus;

public interface ProducerService {
    void sendToFanoutExchange(CarRentStatus message);

}

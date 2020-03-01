package com.example.rentservice.producer;

import com.example.rentservice.message.BillDto;
import com.example.rentservice.message.BillMessage;
import com.example.rentservice.message.Message;
import com.example.rentservice.message.UpdateCarStatus;

public interface ProducerService {
    void sendToFanoutExchangeToCarService(UpdateCarStatus updateCarStatus);
    void sendToFanoutExchangeToClientService(BillMessage billMessage);


}

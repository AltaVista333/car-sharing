package com.example.clientservice.consumer;

import com.example.clientservice.dto.BillDto;
import com.example.clientservice.entity.Bill;
import com.example.clientservice.message.BillMessage;
import com.example.clientservice.message.Message;
import com.example.clientservice.service.BillService;
import com.example.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    private ClientService clientService;
    private BillService billService;


    public ConsumerService(ClientService clientService, BillService billService) {
        this.clientService = clientService;
        this.billService = billService;
    }

    @RabbitListener(queues = "${toClient}")
    public void handleQueueAMessageReception(BillMessage billMessage) {
        log.info("Receive bill from rent service : {}", billMessage.getRentId());
        billService.addBill(billMessage.getClientId(),
                Bill.builder()
                        .paidOff(billMessage.getPaidOff())
                        .rentId(billMessage.getRentId())
                        .amount(billMessage.getAmount())
                        .build());
    }
}

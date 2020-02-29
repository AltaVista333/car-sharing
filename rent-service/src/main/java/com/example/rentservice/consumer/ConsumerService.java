package com.example.rentservice.consumer;

import com.example.rentservice.entity.Rent;
import com.example.rentservice.entity.RentStatus;
import com.example.rentservice.message.CarRentStatus;
import com.example.rentservice.message.Message;
import com.example.rentservice.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class ConsumerService {

    private RentService rentService;

    public ConsumerService(RentService rentService) {
        this.rentService = rentService;
    }

    @RabbitListener(queues = "${fromCar}")
    @Transactional
    public void handleQueueAMessageReception2(CarRentStatus carStatus) {
        log.info("Message received from Car-service: {}", carStatus.getStatus());
        Long rentId = carStatus.getRentId();
        Optional<Rent> rentById = rentService.findRentById(rentId);
        if("RENTED".equals(carStatus.getStatus())){
            log.info("set rent status to ongoing");
            rentById.ifPresent(rent -> {
                rent.setStatus(RentStatus.ONGOING);
                log.info(rent.getStatus().name());
                rentService.updateRent(rent);
            });
        } else if ("ACTIVE".equals(carStatus.getStatus())){
            log.info("set rent status to active");
            rentById.ifPresent(rent -> {
                rent.setStatus(RentStatus.ENDED);
                rent.setEndRent(LocalDateTime.now());
                log.info(rent.getStatus().name());
                rentService.updateRent(rent);
            });
        } else {
            rentById.ifPresent(rent -> {
            rent.setStatus(RentStatus.REJECTED);
            rentService.updateRent(rent);
            });
        }


    }

    @RabbitListener(queues = "${fromClient}")
    public void handleQueueAMessageReception(Message message) {
        log.info("Message received from Client-service: " + message.getName());


    }
}
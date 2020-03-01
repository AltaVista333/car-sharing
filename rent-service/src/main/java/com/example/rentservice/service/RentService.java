package com.example.rentservice.service;

import com.example.rentservice.entity.Rent;
import com.example.rentservice.message.CarRentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface RentService {

    Rent createRent(Rent rent);
    Optional<Rent> findRentById(Long id);
    Rent updateRent(Rent rent);
    Rent closeRent(Long id);
    boolean checkOngoingClientRent(Long id);
    BigDecimal calculateBillForClient(Long carId, Long rentId);
    void handleCarQueue(CarRentStatus carStatus);
}

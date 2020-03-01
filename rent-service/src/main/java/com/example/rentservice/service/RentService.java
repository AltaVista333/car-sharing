package com.example.rentservice.service;

import com.example.rentservice.entity.Rent;

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
    public BigDecimal calculateBillForClient(Long carId, Long rentId);
}

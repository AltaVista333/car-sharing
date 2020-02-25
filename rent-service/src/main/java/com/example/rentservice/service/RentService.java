package com.example.rentservice.service;

import com.example.rentservice.entity.Rent;

import java.util.UUID;

public interface RentService {

    UUID createRent(Rent rent);
}

package com.example.clientservice.service;

import com.example.clientservice.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client addClient(Client car);
    List<Client> getAllClients();
    Client findById(Long id);
    Client updateClient(Client car, Long id);
    Optional<Client> findByIdForQueue(Long id);
}

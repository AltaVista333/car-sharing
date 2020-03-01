package com.example.clientservice.service.impl;

import com.example.clientservice.entity.Client;
import com.example.clientservice.exception.ClientNotFoundException;
import com.example.clientservice.exception.DatabeseNotFountException;
import com.example.clientservice.exception.ErrorMessages;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.repository = clientRepository;
    }

    @Override
    @Transactional
    public Client addClient(Client car) {
        return repository.save(car);
    }

    @Override
    @Transactional
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Client findById(Long id) {
        log.info("Find client by id: {}", id);
        return repository.findById(id).orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
    }

    @Override
    @Transactional
    public Client updateClient(Client client, Long id) {
        return repository.findById(id)
                .map(updatedClient -> {
                    updatedClient.setId(client.getId());
                    updatedClient.setName(client.getName());
                    updatedClient.setSurname(client.getSurname());
                    updatedClient.setEmail(client.getEmail());
                    return repository.save(updatedClient);
                }).orElseThrow(() -> new DatabeseNotFountException(ErrorMessages.NOT_FOUND));
    }

    @Override
    @Transactional
    public Optional<Client> findByIdForQueue(Long id) {
        return repository.findById(id);
    }
}

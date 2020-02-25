package com.example.clientservice.service.impl;

import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
        return repository.findById(id).orElseThrow(() -> new ServiceException("No such client"));
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
                    updatedClient.setStatus(client.getStatus());
                    return repository.save(updatedClient);
                }).orElseThrow(() -> new ServiceException("No such client"));
    }

    @Override
    @Transactional
    public Optional<Client> findByIdForQueue(Long id) {
        return repository.findById(id);
    }
}

package com.example.clientservice.controller;

import com.example.clientservice.converter.GenericConverter;
import com.example.clientservice.dto.ClientCreateDto;
import com.example.clientservice.dto.ClientDto;
import com.example.clientservice.entity.Client;
import com.example.clientservice.message.Message;
import com.example.clientservice.producer.ProducerService;
import com.example.clientservice.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("client")
@Slf4j
public class ClientController {

    private ClientService clientService;
    private GenericConverter<Client, ClientCreateDto> clientCreateConverter;
    private GenericConverter<Client, ClientDto> clientConverter;
    ProducerService producerService;

    public ClientController(ClientService clientService, GenericConverter<Client, ClientCreateDto> clientCreateConverter, GenericConverter<Client, ClientDto> clientConverter, ProducerService producerService) {
        this.clientService = clientService;
        this.clientCreateConverter = clientCreateConverter;
        this.clientConverter = clientConverter;
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<Void> addClient(@RequestBody @Valid ClientCreateDto createDto){
        Client client = clientCreateConverter.toEntity(createDto);
        clientService.addClient(client);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public List<ClientDto> getAllUsers(){
        return clientConverter.toDto(clientService.getAllClients());
    }

    @GetMapping(path = "/{id}")
    public ClientDto getUserByID(@PathVariable("id") Long id){
        Client client = clientService.findById(id);
        return clientConverter.toDto(client);
    }
}

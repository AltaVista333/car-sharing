package com.example.rentservice.service.impl;

import com.example.rentservice.client.ClientFeign;
import com.example.rentservice.dto.ClientDto;
import com.example.rentservice.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientFeign clientFeign;

    public ClientServiceImpl(ClientFeign clientFeign) {
        this.clientFeign = clientFeign;
    }

    @Override
    public ClientDto client(Long userId) {
        return clientFeign.getUserByID(userId);
    }
}

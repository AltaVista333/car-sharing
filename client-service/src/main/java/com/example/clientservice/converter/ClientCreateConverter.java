package com.example.clientservice.converter;

import com.example.clientservice.dto.ClientCreateDto;
import com.example.clientservice.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientCreateConverter implements GenericConverter<Client, ClientCreateDto> {

    @Override
    public Client toEntity(ClientCreateDto dto) {
        return Client.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public ClientCreateDto toDto(Client entity) {
        return null;
    }
}

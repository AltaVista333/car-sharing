package com.example.clientservice.converter;

import com.example.clientservice.dto.ClientDto;
import com.example.clientservice.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientConverter implements GenericConverter<Client, ClientDto> {

    @Override
    public Client toEntity(ClientDto dto) {
        return Client.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public ClientDto toDto(Client entity) {
        return ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
    }
}

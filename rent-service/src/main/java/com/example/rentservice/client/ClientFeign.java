package com.example.rentservice.client;

import com.example.rentservice.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientFeign {

    @GetMapping(path = "/client/{id}")
    public ClientDto getUserByID(@PathVariable("id") Long id);
}

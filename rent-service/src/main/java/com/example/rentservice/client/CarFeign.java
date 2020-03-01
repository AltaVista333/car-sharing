package com.example.rentservice.client;

import com.example.rentservice.dto.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CAR-SERVICE")
public interface CarFeign {

    @GetMapping("car/{id}")
    public CarDto getCarById(@PathVariable("id") Long id);
}


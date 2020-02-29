package com.example.rentservice.message;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CheckCarStatus extends Message {

    private Long carId;

    public CheckCarStatus(UUID id, Long carId) {
        super(id, "CarStatus");
        this.carId = carId;
    }
}

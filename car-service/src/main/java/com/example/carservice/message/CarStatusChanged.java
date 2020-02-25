package com.example.carservice.message;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CarStatusChanged extends Message{




    public CarStatusChanged(UUID uuid, Long carId, String message) {
        super(uuid, message,carId);

    }
}

package com.example.clientservice.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarStatus {

    private Long carId;
    private String status;

    public UpdateCarStatus(String status, Long carId) {
        this.status = status;
        this.carId = carId;
    }
}

package com.example.rentservice.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarRentStatus implements Serializable {
    private String status;
    private Long carId;
    private Long rentId;


}

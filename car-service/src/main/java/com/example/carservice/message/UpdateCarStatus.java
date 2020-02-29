package com.example.carservice.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarStatus implements Serializable {

    private Long carId;
    private String status;
    private Long rentId;


}

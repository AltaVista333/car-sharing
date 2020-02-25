package com.example.carservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@ToString
public class CarDto {

    private Long id;

    @NotNull
    private String model;

    @NotNull
    private String carStatus;

    @NotNull
    private String adress;

    public CarDto(Long id, @NotNull String model, @NotNull String carStatus, @NotNull String adress) {
        this.id = id;
        this.model = model;
        this.carStatus = carStatus;
        this.adress = adress;
    }
}

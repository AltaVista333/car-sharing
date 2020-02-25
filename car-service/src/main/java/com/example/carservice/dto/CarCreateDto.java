package com.example.carservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarCreateDto {
    @NotNull
    private String model;

    @NotNull
    private String adress;

    public CarCreateDto(@NotNull String model, @NotNull String adress) {
        this.model = model;
        this.adress = adress;
    }
}

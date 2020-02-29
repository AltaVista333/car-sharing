package com.example.rentservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

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
    @Positive
    private Integer coordX;

    @NotNull
    @Positive
    private Integer coordY;

    @NotNull
    @Positive
    private BigDecimal minutePrice;

    public CarDto(Long id, @NotNull String model, @NotNull String carStatus, @NotNull @Positive Integer coordX, @NotNull @Positive Integer coordY, @NotNull @Positive BigDecimal minutePrice) {
        this.id = id;
        this.model = model;
        this.carStatus = carStatus;
        this.coordX = coordX;
        this.coordY = coordY;
        this.minutePrice = minutePrice;
    }
}

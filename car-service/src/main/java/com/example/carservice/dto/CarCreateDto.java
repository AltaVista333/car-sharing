package com.example.carservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarCreateDto {
    @NotNull
    private String model;

    @NotNull
    @Positive
    private Integer coordX;

    @NotNull
    @Positive
    private Integer coordY;

    @NotNull
    @Positive
    private BigDecimal minutePrice;

    public CarCreateDto(@NotNull String model, @NotNull Integer coordX,
                        @NotNull Integer coordY, @NotNull BigDecimal priceInMinute) {
        this.model = model;
        this.coordX = coordX;
        this.coordY = coordY;
        this.minutePrice = priceInMinute;
    }
}

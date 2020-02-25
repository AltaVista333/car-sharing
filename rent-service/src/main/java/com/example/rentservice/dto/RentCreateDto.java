package com.example.rentservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class RentCreateDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long carId;


    public RentCreateDto(@NotNull Long userId, @NotNull Long carId) {
        this.userId = userId;
        this.carId = carId;

    }
}

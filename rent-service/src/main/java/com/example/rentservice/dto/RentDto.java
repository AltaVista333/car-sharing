package com.example.rentservice.dto;

import com.example.rentservice.entity.RentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class RentDto {

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long carId;

    @NotNull
    private String status;

    @NotNull
    private LocalDateTime startRent;

    private LocalDateTime endRent;

    public RentDto(Long id, @NotNull Long userId,
                   @NotNull Long carId, String status,
                   @NotNull LocalDateTime startRent, LocalDateTime endRent) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.status = status;
        this.startRent = startRent;
        this.endRent = endRent;
    }
}
